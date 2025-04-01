package com.sj.unittestworkshop.composeviews

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sj.unittestworkshop.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImagePickerFromGalleryAndCamera() {
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            imageUri = result.data?.data
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarColors(
                    containerColor = Color.DarkGray,
                    scrolledContainerColor = Color.DarkGray,
                    navigationIconContentColor = Color.White,
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White
                ),
                title = {
                    Text(text = "Image Picker")
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Button(modifier = Modifier
                    .padding(top = 100.dp)
                    .semantics {
                        contentDescription = "btnPickImage"
                    }, onClick = {
                    val intent = Intent(Intent.ACTION_CHOOSER).apply {
                        putExtra(
                            Intent.EXTRA_INTENT,
                            Intent(Intent.ACTION_GET_CONTENT).apply { type = "image/*" })
                        putExtra(Intent.EXTRA_TITLE, "Select Image")
                        putExtra(
                            Intent.EXTRA_INITIAL_INTENTS, arrayOf(
                                Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                            )
                        )
                    }
                    context.startActivity(intent)
                    galleryLauncher.launch("image/*")

                }) {
                    Text("Pick Image")
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (imageUri != null) {
                    Image(
                        // Replace coil with this logic to load bitmap
                        //painter = rememberAsyncImagePainter(model = imageUri),
                        painter = BitmapPainter(imageUri?.let {
                            context.contentResolver.openInputStream(
                                it
                            )?.use { stream -> BitmapFactory.decodeStream(stream) }?.asImageBitmap()
                        }!!),
                        contentDescription = "Selected Image",
                        modifier = Modifier.size(200.dp)
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground), // Replace with a placeholder
                        contentDescription = "Placeholder",
                        modifier = Modifier.size(200.dp)
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ImagePickerPreview() {
    ImagePickerFromGalleryAndCamera()
}
