package com.example.myapplication.tests

import androidx.test.espresso.web.webdriver.Locator
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.myapplication.R
import com.example.myapplication.activities.WifiCheckActivity
import com.example.myapplication.screen.WiFiScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

class WiFiTest : TestCase() {

    companion object {
        private const val HEADER_TEXT: String = "Введение"
        private const val HEADER_LOCATOR: String = "_1"
        private const val ERR_LOCATOR: String = "//*[contains(text(), 'net')]"
        private const val ERR_NET_TEXT: String = "ERR_INTERNET_DISCONNECTED"
    }
    @get:Rule
    val activityRule = ActivityScenarioRule(WifiCheckActivity::class.java)

    @Test
    fun testWifiEnabledFlow() = run {
        step("Enable Wi-Fi") {
            device.network.enable()
        }

        step("Check Wi-Fi is enabled") {
            testLogger.i(device.network.toString())
            WiFiScreen {
                flakySafely {
                    checkButton.click()
                    wifiState.hasText(R.string.wifi_connected)
                }
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
                    withElement(Locator.ID, HEADER_LOCATOR) {
                        containsText(HEADER_TEXT)
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

        step("Check Wi-Fi is disabled") {
            WiFiScreen {
                flakySafely {
                    checkButton.click()
                    wifiState.hasText(R.string.wifi_disconnected)
                }
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
                    withElement(Locator.XPATH, ERR_LOCATOR) {
                        containsText(ERR_NET_TEXT)
                    }
                }
            }
        }
    }

}