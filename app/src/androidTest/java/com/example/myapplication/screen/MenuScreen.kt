package com.example.myapplication.screen

import com.example.myapplication.activities.MainActivity
import com.example.myapplication.R
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.text.KButton

object MenuScreen : KScreen<MenuScreen>(){
    override val layoutId: Int = R.layout.activity_main
    override val viewClass: Class<*> = MainActivity::class.java

    val toDoButton = KButton {withId(R.id.btnTodoList)}
    val wiFiCheckButton = KButton {withId(R.id.btnCheckWifi)}
}