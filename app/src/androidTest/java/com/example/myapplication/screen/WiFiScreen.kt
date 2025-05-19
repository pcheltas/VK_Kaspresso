package com.example.myapplication.screen

import com.example.myapplication.R
import com.example.myapplication.activities.WifiCheckActivity
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.text.KButton

object WiFiScreen : KScreen<WiFiScreen>(){
    override val layoutId: Int = R.layout.activity_wifi_check
    override val viewClass: Class<*> = WifiCheckActivity::class.java

    val checkButton = KButton {withId(R.id.btn_check_wifi)}
    val wifiState = KEditText {withId(R.id.tv_wifi_status)}
}