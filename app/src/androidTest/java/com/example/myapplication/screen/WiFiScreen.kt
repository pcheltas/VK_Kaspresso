package com.example.myapplication.screen

import androidx.test.espresso.web.webdriver.Locator
import com.example.myapplication.R
import com.example.myapplication.activities.WifiCheckActivity
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.web.KWebView

object WiFiScreen : KScreen<WiFiScreen>(){
    override val layoutId: Int = R.layout.activity_wifi_check
    override val viewClass: Class<*> = WifiCheckActivity::class.java

    val checkButton = KButton {withId(R.id.btn_check_wifi)}
    val wifiState = KEditText {withId(R.id.tv_wifi_status)}
    val tutorialButton = KButton {withId(R.id.btn_go_to_kaspresso_tutorial)}
    val page = KWebView {withId(R.id.webView)}
}