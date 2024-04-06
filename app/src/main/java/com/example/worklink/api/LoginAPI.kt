package com.example.worklink.api

import com.example.worklink.models.FactorySignUpResponse
import com.example.worklink.models.FactorySignupRequest
import com.example.worklink.models.StartupSignupRequest
import com.example.worklink.models.StartupSignupResponse
import com.example.worklink.models.WorkerSignupRequest
import com.example.worklink.models.WorkerSignupResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginAPI {
    @POST("/api/worker/signup")
    suspend fun workerSigup(@Body workerSignupRequest: WorkerSignupRequest): Response<WorkerSignupResponse>

    @POST ("/api/startup/signup")
    suspend fun startupsignUp(@Body startupSignup:StartupSignupRequest):Response<StartupSignupResponse>

    @POST("/api/manufacturer/signup")
    suspend fun manufacturerSignup(@Body factorySignupRequest: FactorySignupRequest):Response<FactorySignUpResponse>

}