package com.example.taskapp.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.taskapp.R
import com.example.taskapp.util.PrefsManager
import com.example.taskapp.viewmodel.TaskViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val viewModel: TaskViewModel by viewModels()
    private lateinit var prefs: PrefsManager
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefs = PrefsManager(this)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val fab = findViewById<FloatingActionButton>(R.id.fabAdd)

        viewModel.tasks.value = prefs.loadTasks()

        adapter = TaskAdapter(
            viewModel.tasks.value!!,
            onDelete = { save() },
            onChecked = { save() }
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        fab.setOnClickListener {
            startActivity(Intent(this, AddTaskActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        refreshTasks()
    }

    private fun refreshTasks() {
        val newTasks = prefs.loadTasks()
        viewModel.tasks.value?.clear()
        viewModel.tasks.value?.addAll(newTasks)
        adapter.notifyDataSetChanged()
    }

    private fun save() {
        prefs.saveTasks(viewModel.tasks.value!!)
    }
}
