package com.example.myapplication.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.myapplication.activities.FlakyCounterActivity
import com.example.myapplication.screen.FlakyScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test

class FlakyTest : TestCase() {
    @get:Rule
    val activityRule = ActivityScenarioRule(FlakyCounterActivity::class.java)

    @Test
    fun testFlakyCounter() = run {
        step("Click on increment button") {
            FlakyScreen {
                incrementButton.click()
                counterText.hasText("Counter: 1")
            }
        }
    }

    @Test
    fun testFlakyLongCounter() = run {
        step("Click on increment button") {
            FlakyScreen {
                incrementButton.click()
                flakySafely(timeoutMs = 16_000) {
                    counterText.hasText("Counter: 1")
                }
            }
        }
    }
}