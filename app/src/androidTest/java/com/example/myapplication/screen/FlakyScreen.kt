package com.example.myapplication.screen

import com.example.myapplication.R
import com.example.myapplication.activities.FlakyCounterActivity
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.text.KButton

object FlakyScreen  : KScreen<FlakyScreen>(){
    override val layoutId: Int = R.layout.activity_flaky
    override val viewClass: Class<*> = FlakyCounterActivity::class.java

    val incrementButton = KButton {withId(R.id.btnFlucky)}
    val counterText = KEditText {withId(R.id.counter)}
}