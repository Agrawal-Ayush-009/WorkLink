package com.example.worklink.models

data class CreateGigRequest(
    val description: String,
    val location: Location,
    val pay: Int,
    val skillsRequired: List<String>,
    val workerLimit: Int
)