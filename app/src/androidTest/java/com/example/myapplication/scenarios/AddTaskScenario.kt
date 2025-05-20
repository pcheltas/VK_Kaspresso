package com.example.myapplication.scenarios

import com.example.myapplication.screen.NewTaskScreen
import com.example.myapplication.screen.ToDoScreen
import com.kaspersky.kaspresso.testcases.api.scenario.Scenario
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext

class AddTaskScenario(
    private val name: String,
    private val description: String,
    private val timeHour: Int,
    private val timeMinute: Int
) : Scenario() {
    override val steps: TestContext<Unit>.() -> Unit = {
        step("Open todo screen and verify it is ready") {
            ToDoScreen {
                recyclerView.isDisplayed()
            }
        }

        step("Click on new task button") {
            ToDoScreen {
                newTaskButton.click()
            }
        }

        step("Filling fields with new task data") {
            ToDoScreen {
                NewTaskScreen {
                    nameEdit.replaceText(name)
                    descEdit.replaceText(description)

                }
            }
        }

        step("Setting task time in timepicker") {
            ToDoScreen {
                NewTaskScreen {
                    timePickerButton.click()
                    timePicker.setTime(timeHour, timeMinute)
                    timePickerOkButton.click()
                }
            }
        }

        step("Save new task (clicking save button)") {
            NewTaskScreen {
                saveButton.click()
            }
        }

        step("Verify task was added") {
            ToDoScreen.recyclerView {
                childAt<ToDoScreen.ToDoItem>(0) {
                    taskName {
                        hasText(name)
                    }
                    dueTime {
                        hasText("$timeHour:$timeMinute")
                    }
                }
            }
        }
    }
}