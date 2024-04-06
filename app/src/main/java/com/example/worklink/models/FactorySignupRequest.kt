package com.example.worklink.models

data class FactorySignupRequest(
    val companyName:String,
    val companyEmail:String,
    val password:String,
    val workSector:String,
    val machines:List<Machine>,
    val location:Location,
    val profilePicture:String,
    val gigs:List<String>
)
