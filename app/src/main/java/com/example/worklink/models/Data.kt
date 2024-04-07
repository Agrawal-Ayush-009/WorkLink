package com.example.worklink.models

data class Data(
    val _id: String,
    val companyEmail: String,
    val companyName: String,
    val gigs: List<String>,
    val location: Location,
    val machines: List<Machine2>,
    val password: String,
    val profilePicture: String,
    val workSector: String
)