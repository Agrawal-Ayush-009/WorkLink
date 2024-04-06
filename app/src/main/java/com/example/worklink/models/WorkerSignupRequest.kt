package com.example.worklink.models

data class WorkerSignupRequest(
    val email: String,
    val location: Location,
    val name: String,
    val password: String,
    val profilePicture: String,
    val skills: List<String>
)