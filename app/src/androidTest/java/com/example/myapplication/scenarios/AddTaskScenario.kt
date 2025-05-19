package com.example.myapplication.scenarios

import com.example.myapplication.screen.NewTaskScreen
import com.example.myapplication.screen.ToDoScreen
import com.kaspersky.kaspresso.testcases.api.scenario.Scenario
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext

class AddTaskScenario : Scenario() {
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
                    nameEdit.replaceText("Go to dentist")
                    descEdit.replaceText("Dr. name - Mr. Green")

                }
            }
        }

        step("Setting task time in timepicker") {
            ToDoScreen {
                NewTaskScreen {
                    timePickerButton.click()
                    timePicker.setTime(12, 30)
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
                        hasText("Go to dentist")
                    }
                    dueTime {
                        hasText("12:30")
                    }
                }
            }
        }
    }
}