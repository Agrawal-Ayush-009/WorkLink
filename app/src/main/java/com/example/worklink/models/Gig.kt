package com.example.worklink.models

data class Gig(
    val __v: Int,
    val _id: String,
    val appliedWorkers: List<Any>,
    val companyName: String,
    val description: String,
    val location: Location,
    val pay: Int,
    val skillsRequired: List<String>,
    val workerLimit: Int
)