package com.example.worklink.api

import com.example.worklink.models.WorkerGigsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface MainAPI {
    @GET("/api/worker/getGigs")
    suspend fun getGigsWorker(): Response<WorkerGigsResponse>
}