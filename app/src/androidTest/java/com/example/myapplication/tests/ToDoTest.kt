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
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class ToDoTest(
    private val name: String,
    private val description: String,
    private val hours: Int,
    private val minutes: Int
) : TestCase() {

    private lateinit var scenario: AddTaskScenario

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data() = listOf(
            arrayOf("Buy groceries", "Milk, apples, pork", 18, 30)
        )
    }

    @get:Rule
    val activityRule = ActivityScenarioRule(NewTaskActivity::class.java)

    @Before
    fun setUp() {
        scenario = AddTaskScenario(
            name = "Go to dentist",
            description = "Dr. name - Mr. Green",
            timeHour = 12,
            timeMinute = 30
        )

        run{
            step("Create new task") {
                scenario(scenario)
            }
        }
    }


    @Test
    fun testCompleteTask() = run {

        step("Mask task as completed") {
            ToDoScreen.recyclerView {
                firstChild<ToDoScreen.ToDoItem> {
                    completeButton.click()
                }
            }
        }

        step("Verify text has strike through") {
            ToDoScreen.recyclerView {
                firstChild<ToDoScreen.ToDoItem> {
                    taskName.matches { hasStrikeThroughText() }
                }
            }
        }
    }

    @Test
    fun testDeleteTask() = run {

        step("Click on delete button") {
            ToDoScreen.recyclerView {
                firstChild<ToDoScreen.ToDoItem> {
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

        step("Click on task and change task data") {
            ToDoScreen.recyclerView {
                firstChild<ToDoScreen.ToDoItem> {
                    click()
                }
            }
            NewTaskScreen {
                nameEdit.replaceText(name)
                descEdit.replaceText(description)
                timePickerButton.click()
                timePicker.setTime(hours, minutes)
                timePickerOkButton.click()
                saveButton.click()
            }
        }

        step("Verify task has been changed") {
            ToDoScreen.recyclerView {
                firstChild<ToDoScreen.ToDoItem> {
                    taskName.hasText(name)
                    dueTime.hasText("$hours:$minutes")
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