package com.example.taskapp.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.taskapp.R
import com.example.taskapp.model.Task
import com.example.taskapp.util.PrefsManager
import com.example.taskapp.viewmodel.TaskViewModel

class AddTaskActivity : AppCompatActivity() {

    private val viewModel: TaskViewModel by viewModels()
    private lateinit var prefs: PrefsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        prefs = PrefsManager(this)

        val edtTask = findViewById<EditText>(R.id.edtTask)
        val btnSave = findViewById<Button>(R.id.btnSave)

        edtTask.setText(viewModel.inputText.value ?: "")

        btnSave.setOnClickListener {
            val title = edtTask.text.toString()

             if (title.isBlank()) {
                Toast.makeText(this, "Task cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val tasks = prefs.loadTasks()
            tasks.add(Task(System.currentTimeMillis(), title, false))
            prefs.saveTasks(tasks)
            finish()
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.inputText.value =
            findViewById<EditText>(R.id.edtTask).text.toString()
    }
}
