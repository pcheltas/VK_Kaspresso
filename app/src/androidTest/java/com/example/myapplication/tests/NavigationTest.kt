package com.example.myapplication.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.myapplication.R
import com.example.myapplication.activities.MainActivity
import com.example.myapplication.scenarios.MenuScenario
import com.example.myapplication.screen.MenuScreen
import com.example.myapplication.screen.ToDoScreen
import com.example.myapplication.screen.WiFiScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class NavigationTest : TestCase() {

    private lateinit var scenario: MenuScenario

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        scenario = MenuScenario()

        run {
            step("Verify app is prepared") {
                scenario(scenario)
            }
        }
    }

    @Test
    fun testNavigateToToDoScreen() = run {

        step("Verify todo navigation button exists") {
            MenuScreen {
                toDoButton {
                    isVisible()
                    hasText(R.string.todo_list)
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

        step("Verify wifi navigation button exists") {
            MenuScreen {
                wiFiCheckButton {
                    isVisible()
                    hasText(R.string.wifi_check)
                }
            }
        }

        step("Press on wifi nav button and check navigation") {
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

