package com.example.myapplication.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.myapplication.activities.MainActivity
import com.example.myapplication.scenarios.MenuScenario
import com.example.myapplication.screen.MenuScreen
import com.example.myapplication.screen.ToDoScreen
import com.example.myapplication.screen.WiFiScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test


class NavigationTest : TestCase() {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testNavigateToToDoScreen() = run {
        step("Verify app is prepared") {
            scenario( MenuScenario () )
        }

        step("Verify todo navigation button exists") {
            MenuScreen {
                toDoButton {
                    isVisible()
                    hasText("Task list")
                }
            }
        }

        step("Press on todo nav button and check navigation")
        {
            MenuScreen {
                toDoButton.click()
            }
            ToDoScreen {
                recyclerView.isVisible()
            }
        }
    }

    @Test
    fun testNavigateToWiFiScreen() = run {
        step("Verify app is prepared") {
            scenario( MenuScenario () )
        }

        step("Verify wifi navigation button exists") {
            MenuScreen {
                wiFiCheckButton {
                    isVisible()
                    hasText("Check WiFi")
                }
            }
        }

        step("Press on wifi nav button and check navigation")
        {
            MenuScreen {
                wiFiCheckButton.click()
            }
            WiFiScreen {
                checkButton.isVisible()
                wifiState.isVisible()
            }
        }
    }
}

