package com.example.myapplication.scenarios

import com.example.myapplication.R
import com.example.myapplication.screen.MenuScreen
import com.kaspersky.kaspresso.testcases.api.scenario.Scenario
import com.kaspersky.kaspresso.testcases.core.testcontext.TestContext

class MenuScenario : Scenario() {
    override val steps: TestContext<Unit>.() -> Unit = {
        step("Open menu screen and check") {
            MenuScreen {
                toDoButton {
                    isVisible()
                    hasText(R.string.todo_list)
                }

                wiFiCheckButton {
                    isVisible()
                    hasText(R.string.wifi_check)
                }
            }
        }
    }
}