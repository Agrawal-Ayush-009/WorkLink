package com.example.worklink.models

data class StartupSignupRequest(
    val companyName:String,
    val companyEmail:String,
    val password:String,
    val workSector:String,
    val location:Location,
    val profilePicture:String
)
