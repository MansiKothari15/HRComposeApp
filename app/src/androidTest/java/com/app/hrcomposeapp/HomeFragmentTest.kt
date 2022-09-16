package com.app.hrcomposeapp

import androidx.activity.viewModels
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.app.hrcomposeapp.viewmodels.HomeViewModel
import com.app.hrcomposeapp.views.AppMainScreen
import com.app.hrcomposeapp.views.HomeActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
@HiltAndroidTest
class HomeFragmentTest {
    @get:Rule()
    var hiltTestRule = HiltAndroidRule(this)

    @get:Rule()
    var composeTestRule = createAndroidComposeRule<HomeActivity>()

    @Before
    fun setup() {
        hiltTestRule.inject()
        composeTestRule.setContent {
            AppMainScreen(composeTestRule.activity.viewModels<HomeViewModel>().value)
        }
    }

    @Test
    fun open_drawer_test() {
        val navDrawerDesc = "navigation_drawer"
        composeTestRule.onNodeWithContentDescription(navDrawerDesc).performClick()
    }
}