package com.example.myapplication.tests

import androidx.test.espresso.web.webdriver.Locator
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.myapplication.activities.WifiCheckActivity
import com.example.myapplication.screen.WiFiScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

class WiFiTest : TestCase() {
    @get:Rule
    val activityRule = ActivityScenarioRule(WifiCheckActivity::class.java)

    @Test
    fun testWifiEnabledFlow() = run {
        step("Enable Wi-Fi") {
            device.network.enable()
        }

        step("Check Wi-Fi is enabled") {
            WiFiScreen {
                checkButton.click()
                wifiState.hasText("Wi-Fi connected")
            }
        }
        step("Go to web tutorial page") {
            WiFiScreen {
                tutorialButton.click()
            }
        }
        step("Verify tutorial page was loaded successfully") {
            WiFiScreen {
                page {
                    withElement(Locator.ID, "_1") {
                        containsText("Введение")
                    }
                }
            }
        }
    }

    @Test
    fun testWifiDisabledFlow() = run {
        step("Disable Wi-Fi") {
            device.network.disable()
        }

        step("Check Wi-Fi is disables") {
            WiFiScreen {
                checkButton.click()
                wifiState.hasText("Wi-Fi disconnected")
            }
        }
        step("Go to web tutorial page") {
            WiFiScreen {
                tutorialButton.click()
            }
        }
        step("Verify tutorial page was not loaded") {
            WiFiScreen {
                page {
                    withElement(Locator.XPATH, "//*[contains(text(), 'net')]") {
                        containsText("ERR_INTERNET_DISCONNECTED")
                    }
                }
            }
        }
    }

}