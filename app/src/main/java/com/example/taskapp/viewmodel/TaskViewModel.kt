package com.example.taskapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.taskapp.model.Task

class TaskViewModel : ViewModel() {

    val tasks = MutableLiveData<MutableList<Task>>(mutableListOf())
    val inputText = MutableLiveData<String>()
}
