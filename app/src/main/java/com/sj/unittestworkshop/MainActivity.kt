package com.sj.unittestworkshop

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sj.unittestworkshop.composeviews.EspressoUnitTestView
import com.sj.unittestworkshop.composeviews.ImagePickerFromGalleryAndCamera

class MainActivity : AppCompatActivity() {

    private lateinit var navHostController: NavHostController

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            navHostController = rememberNavController()

            Surface(
                modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
            ) {
                NavigateToSettingsScreen(
                    navHostController = navHostController
                )
            }
        }

    }

    @Composable
    fun NavigateToSettingsScreen(
        navHostController: NavHostController,
        startDestination: String = ScreenNames.MainHomeScreen.route
    ) = NavHost(navController = navHostController, startDestination = startDestination)
    {
        composable(route = ScreenNames.MainHomeScreen.route) {
            EspressoUnitTestView(navHostController)
        }

        composable(route = ScreenNames.ImagePickerScreen.route) {
            ImagePickerFromGalleryAndCamera()
        }
    }

    sealed class ScreenNames(val route: String) {
        data object MainHomeScreen : ScreenNames("MainHome")
        data object ImagePickerScreen : ScreenNames("ImagePicker")
    }

}