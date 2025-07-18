package com.sj.unittestworkshop

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.espresso.intent.rule.IntentsRule
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import kotlin.time.Duration.Companion.seconds

class MainActivityTest{

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @get:Rule
    val intentsRule = IntentsRule()

    @Test
    fun testTextDisplay() {
        composeTestRule.onNodeWithContentDescription("labelQuote").assertIsDisplayed()
        composeTestRule.onNodeWithText("My Quote!").assertExists()
        runBlocking {
            delay(2.seconds.inWholeMilliseconds)
        }
    }

    @Test
    fun testNextButton_expectedCorrectQuote(){

        //Arrange
        val initialQuote = "Don’t ever let somebody tell you, you can’t do something. Not even me. Alright? – Chris Gardner"
        val nextQuote = "I am strong because I’ve been weak. I am fearless because I’ve been afraid. I am wise because I’ve been foolish. – Chris Gardner"

        //Act
        composeTestRule.onNodeWithContentDescription("buttonNextQuote").performClick()
        //Assert
        composeTestRule.onNodeWithContentDescription("labelQuoteValue").assertTextEquals(nextQuote)
        composeTestRule.onNodeWithText(nextQuote).assertIsDisplayed()

        //Act
        composeTestRule.onNodeWithContentDescription("buttonPrevQuote").performClick()
        //Assert
        composeTestRule.onNodeWithContentDescription("labelQuoteValue").assertTextEquals(initialQuote)

        runBlocking {
            delay(2.seconds.inWholeMilliseconds)
        }

        composeTestRule.onNodeWithText(initialQuote).assertIsDisplayed()
    }

    @Test
    fun testRadioButton_expectedGenderSelection(){

        composeTestRule.onNodeWithContentDescription("radioButtonMale").assertIsSelected()

        composeTestRule.onNodeWithContentDescription("radioButtonFemale").performClick()

        composeTestRule.onNodeWithContentDescription("radioButtonMale").assertIsNotSelected()

        composeTestRule.onNodeWithContentDescription("radioButtonFemale").assertIsSelected()

        runBlocking {
            delay(2.seconds.inWholeMilliseconds)
        }
    }

    @Test
    fun testOutlinedTextField_expectedInvalidMobileNumberError(){

        val errorMessage = "Invalid mobile number. Must be 10 digits."
        composeTestRule.onNodeWithContentDescription("textFieldMobile").performTextInput("8248482")
        composeTestRule.onNodeWithContentDescription("labelMobileNumberError").assertIsDisplayed()
        composeTestRule.onNodeWithContentDescription("labelMobileNumberError").assertTextEquals(errorMessage)
        runBlocking {
            delay(2.seconds.inWholeMilliseconds)
        }
    }

    @Test
    fun testOutlinedTextField_expectedValidMobileNumberError(){

        composeTestRule.onNodeWithContentDescription("textFieldMobile").performTextInput("8248482667")
        composeTestRule.onNodeWithContentDescription("labelMobileNumberError").assertIsNotDisplayed()
        runBlocking {
            delay(2.seconds.inWholeMilliseconds)
        }
    }

    @Test
    fun `navigate to image picker screen`(){
        composeTestRule.onNodeWithContentDescription("buttonImagePicker").performClick()

        // Assert
        composeTestRule.onNodeWithText("Image Picker").assertExists()

        runBlocking {
            delay(2.seconds.inWholeMilliseconds)
        }
    }



    /*@Test
    fun testImagePickerButton() {
        composeTestRule.setContent {
            NavigationApp()
        }

        // Act
        composeTestRule.onNodeWithContentDescription("btnPickImage").performClick()
        // Assert
        Intents.intended(hasAction(Intent.ACTION_CHOOSER))
    }

    @Composable
    fun NavigationApp() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = MainActivity.ScreenNames.ImagePickerScreen) {
            composable(MainActivity.ScreenNames.ImagePickerScreen.route) { ImagePickerFromGalleryAndCamera() }

        }
    }*/

}