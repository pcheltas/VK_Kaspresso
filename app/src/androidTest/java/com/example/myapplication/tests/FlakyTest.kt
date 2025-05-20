package com.example.myapplication.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.myapplication.activities.FlakyCounterActivity
import com.example.myapplication.screen.FlakyScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

class FlakyTest : TestCase() {
    companion object {
        private const val COUNTER_1: String = "Counter: 1"
        private const val TIMEOUT: Long = 16_000
    }

    @get:Rule
    val activityRule = ActivityScenarioRule(FlakyCounterActivity::class.java)

    @Test
    fun testFlakyCounter() = run {
        step("Click on increment button") {
            FlakyScreen {
                incrementButton.click()
                counterText.hasText(COUNTER_1)
            }
        }
    }

    @Test
    fun testFlakyLongCounter() = run {
        step("Click on increment button") {
            FlakyScreen {
                incrementButton.click()
                flakySafely(TIMEOUT) {
                    counterText.hasText(COUNTER_1)
                }
            }
        }
    }
}