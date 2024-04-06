package com.example.worklink.api

import com.example.worklink.models.WorkerSignupRequest
import com.example.worklink.models.WorkerSignupResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginAPI {
    @POST("/api/worker/signup")
    suspend fun workerSigup(@Body workerSignupRequest: WorkerSignupRequest): Response<WorkerSignupResponse>
}