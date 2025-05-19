package com.example.myapplication.screen

import android.widget.TimePicker
import androidx.test.espresso.matcher.ViewMatchers
import com.example.myapplication.R
import com.example.myapplication.activities.NewTaskActivity
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.picker.time.KTimePicker
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matchers.equalTo

object NewTaskScreen : KScreen<NewTaskScreen>() {
    override val layoutId: Int = R.layout.fragment_new_task_sheet
    override val viewClass: Class<*> = NewTaskActivity::class.java

    val taskTitle: KTextView = KTextView { withId(R.id.taskTitle) }
    val nameEdit: KEditText = KEditText { withId(R.id.name) }
    val descEdit: KEditText = KEditText { withId(R.id.desc) }
    val timePickerButton: KButton = KButton { withId(R.id.timePickerButton) }
    val saveButton: KButton = KButton { withId(R.id.saveButton) }
    val timePicker = KTimePicker { withClassName(equalTo(TimePicker::class.java.name)) }
    val timePickerOkButton = KButton {withMatcher(ViewMatchers.withText("OK"))}
}