package com.sj.unittestworkshop.composeviews

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun EspressoUnitTestView() {

    val quote by remember {
        mutableStateOf(
            arrayOf(
                "Don’t ever let somebody tell you, you can’t do something. Not even me. Alright? – Chris Gardner",
                "I am strong because I’ve been weak. I am fearless because I’ve been afraid. I am wise because I’ve been foolish. – Chris Gardner",
                "The balance in your life is more important than the balance in your checking account. – Chris Gardner",
                "There is no plan B for passion. – Chris Gardner"
            )
        )
    }

    val quoteIndex = remember {
        mutableIntStateOf(0)
    }

    val gender = remember {
        mutableStateOf("Male")
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
                    Text(text = "Espresso Unit Test")
                }
            )
        },
        content = { paddingValues ->
            // Content of your screen
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                Text(
                    text = "My Quote!",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 100.dp)
                        .fillMaxWidth()
                        .semantics {
                            contentDescription = "labelQuote"
                        },
                )

                Text(
                    text = quote[quoteIndex.intValue],
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 30.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 10.dp, start = 20.dp, end = 20.dp)
                        .fillMaxWidth()
                        .semantics {
                            contentDescription = "labelQuoteValue"
                        },
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    Arrangement.SpaceAround
                ) {

                    Button(onClick = {
                        if (quoteIndex.intValue > 0) {
                            quoteIndex.intValue--
                        }
                    },
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .semantics {
                                contentDescription = "buttonPrevQuote"
                            }) {
                        Text(text = "Previous Quote")
                    }

                    Button(
                        onClick = {
                            if (quoteIndex.intValue < quote.size - 1) {
                                quoteIndex.intValue++
                            }
                        },
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .semantics {
                                contentDescription = "buttonNextQuote"
                            }) {
                        Text(text = "Next Quote")
                    }
                }


                Row(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Absolute.SpaceEvenly
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Male", modifier = Modifier.semantics {
                            contentDescription = "labelGenderMale"
                        })
                        RadioButton(selected = gender.value == "Male", onClick = {
                            gender.value = "Male"
                        }, modifier = Modifier.semantics {
                            contentDescription = "radioButtonMale"
                        })
                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Female", modifier = Modifier.semantics {
                            contentDescription = "labelGenderFemale"
                        })
                        RadioButton(selected = gender.value == "Female", onClick = {
                            gender.value = "Female"
                        }, modifier = Modifier.semantics {
                            contentDescription = "radioButtonFemale"
                        })
                    }
                }

                MobileNumberInput()


            }

        }
    )
}

@Composable
fun MobileNumberInput() {
    var mobileNumber by remember { mutableStateOf("") }
    var mobileNumberError by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = mobileNumber,
            onValueChange = {
                mobileNumber = it
                mobileNumberError = validateMobileNumber(it)
            },
            label = { Text("Mobile Number") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            isError = mobileNumberError.isNotEmpty(),
            modifier = Modifier.fillMaxWidth().semantics {
                contentDescription = "textFieldMobile"
            }
        )

        if (mobileNumberError.isNotEmpty()) {
            Text(
                text = mobileNumberError,
                color = Color.Red,
                modifier = Modifier.padding(start = 8.dp).semantics {
                    contentDescription = "labelMobileNumberError"
                }
            )
        }
    }
}

fun validateMobileNumber(mobileNumber: String): String {
    if (mobileNumber.isEmpty()) {
        return "" // No error if empty
    }

    if (!mobileNumber.matches(Regex("^[0-9]{10}$"))) {
        return "Invalid mobile number. Must be 10 digits."
    }

    return "" // No error
}