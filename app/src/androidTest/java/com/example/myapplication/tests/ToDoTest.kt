package com.example.myapplication.tests

import android.view.View
import android.widget.TextView
import android.graphics.Paint
import androidx.test.espresso.matcher.ViewMatchers.hasChildCount
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.myapplication.activities.NewTaskActivity
import com.example.myapplication.scenarios.AddTaskScenario
import com.example.myapplication.screen.NewTaskScreen
import com.example.myapplication.screen.ToDoScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test

class ToDoTest : TestCase() {
    @get:Rule
    val activityRule = ActivityScenarioRule(NewTaskActivity::class.java)

    @Test
    fun testAddNewTask() = run {
        step("Create new task") {
            scenario(AddTaskScenario())
        }
    }

    @Test
    fun testCompleteTask() = run {
        step("Create new task") {
            scenario(AddTaskScenario())
        }

        step("Mask task as completed") {
            ToDoScreen.recyclerView {
                childAt<ToDoScreen.ToDoItem>(0) {
                    completeButton.click()
                }
            }
        }

        step("Verify text has strike through") {
            ToDoScreen.recyclerView {
                childAt<ToDoScreen.ToDoItem>(0) {
                    taskName.matches { hasStrikeThroughText() }
                }
            }
        }
    }

    @Test
    fun testDeleteTask() = run {
        step("Create new task") {
            scenario(AddTaskScenario())
        }

        step("Click on delete button") {
            ToDoScreen.recyclerView {
                childAt<ToDoScreen.ToDoItem>(0) {
                    deleteButton.click()
                }
            }
        }

        step("Verify task is deleted") {
            ToDoScreen.recyclerView {
                hasChildCount(0)
            }
        }
    }

    @Test
    fun testEditTask() = run {
        step("Create new task") {
            scenario(AddTaskScenario())
        }

        step("Click on task and change task data") {
            ToDoScreen.recyclerView {
                childAt<ToDoScreen.ToDoItem>(0) {
                    click()
                }
            }
            NewTaskScreen {
                nameEdit.replaceText("Buy groceries")
                descEdit.replaceText("Milk, apples, pork")
                timePickerButton.click()
                timePicker.setTime(18, 30)
                timePickerOkButton.click()
                saveButton.click()
            }
        }

        step("Verify task has been changed") {
            ToDoScreen.recyclerView {
                childAt<ToDoScreen.ToDoItem>(0) {
                    taskName.hasText("Buy groceries")
                    dueTime.hasText("18:30")
                }
            }
        }
    }

    private fun hasStrikeThroughText(): Matcher<View> = object : TypeSafeMatcher<View>() {
        override fun describeTo(description: Description) {
            description.appendText("has strike through text")
        }

        override fun matchesSafely(view: View): Boolean {
            if (view !is TextView) return false
            return (view.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG) != 0
        }
    }
}