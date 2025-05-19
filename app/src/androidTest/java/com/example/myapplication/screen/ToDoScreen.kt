package com.example.myapplication.screen

import android.view.View
import com.example.myapplication.R
import com.example.myapplication.activities.NewTaskActivity
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.common.views.KView
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KTextView
import io.github.kakaocup.kakao.image.KImageView
import org.hamcrest.Matcher

object ToDoScreen : KScreen<ToDoScreen>() {
    override val layoutId: Int = R.layout.activity_bottom_dialog
    override val viewClass: Class<*> = NewTaskActivity::class.java

    val recyclerView = KRecyclerView(
        builder = { withId(R.id.todoListRecyclerView) },
        itemTypeBuilder = { itemType(::ToDoItem) }
    )

    val newTaskButton = KView { withId(R.id.newTaskButton) }

    class ToDoItem(parent: Matcher<View>) : KRecyclerItem<ToDoItem>(parent) {
        val taskName = KTextView(parent) { withId(R.id.name) }
        val dueTime = KTextView(parent) { withId(R.id.dueTime) }
        val completeButton = KImageView(parent) { withId(R.id.completeButton) }
        val deleteButton = KImageView(parent) { withId(R.id.deleteButton) }
        val container = KView(parent) { withId(R.id.taskCellContainer) }
    }
}