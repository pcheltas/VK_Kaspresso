package com.example.myapplication.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.myapplication.activities.MainActivity
import com.example.myapplication.screen.MenuScreen
import com.example.myapplication.screen.PermissionSuccessScreen
import com.kaspersky.kaspresso.device.permissions.Permissions
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test


class PermissionTest : TestCase() {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testPermissionGranted() = run {
        step("Click on request permission button") {
            MenuScreen {
                requestPermissionButton.click()
            }
        }

        step("Accept permission") {
            device.permissions.clickOn(Permissions.Button.ALLOW_FOREGROUND)
        }

        step ("Verify permission activity started") {
            PermissionSuccessScreen {
                permissionText.isVisible()
            }
        }
    }

    @Test
    fun testPermissionNotGranted() = run {
        step("Click on request permission button") {
            MenuScreen {
                requestPermissionButton.click()
            }
        }

        step("Decline permission") {
            device.permissions.clickOn(Permissions.Button.DENY)
        }

        step ("Verify permission activity started") {
            MenuScreen.requestPermissionButton.isVisible()
        }
    }
}