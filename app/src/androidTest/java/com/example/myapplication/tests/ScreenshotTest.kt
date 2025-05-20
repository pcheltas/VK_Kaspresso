package com.example.myapplication.tests

import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.example.myapplication.activities.NewTaskActivity
import com.example.myapplication.screen.ToDoScreen
import com.kaspersky.kaspresso.testcases.api.testcase.DocLocScreenshotTestCase
import org.junit.Rule
import org.junit.Test

class ScreenshotTest  : DocLocScreenshotTestCase(locales = "en") {

    @get:Rule
    val activityRule = activityScenarioRule<NewTaskActivity>()

    @get:Rule
    val permissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        android.Manifest.permission.READ_EXTERNAL_STORAGE,
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    )

    @Test
    fun takeScreenshots() = run {
        step("Take initial state screenshots of the task list page") {
            ToDoScreen {
                recyclerView.isVisible()
                newTaskButton.isVisible()
                captureScreenshot("Task list page initial screenshot")
            }
        }
    }
}