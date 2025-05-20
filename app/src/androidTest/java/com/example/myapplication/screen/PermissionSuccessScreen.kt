package com.example.myapplication.screen

import com.example.myapplication.R
import com.example.myapplication.activities.PermissionActivity
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.text.KTextView

object PermissionSuccessScreen : KScreen<PermissionSuccessScreen>(){
    override val layoutId: Int = R.layout.activity_permission_success
    override val viewClass: Class<*> = PermissionActivity::class.java

    val permissionText: KTextView = KTextView { withId(R.id.permission_text) }
}