package com.example.taskapp.model


data class Task(
    val id: Long,
    val title: String,
    var isCompleted: Boolean
)
