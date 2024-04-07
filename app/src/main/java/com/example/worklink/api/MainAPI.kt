package com.example.worklink.api

import com.example.worklink.models.CreateGigRequest
import com.example.worklink.models.CreateGigResponse
import com.example.worklink.models.ManufacturerGigResponse
import com.example.worklink.models.WorkerGigsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface MainAPI {
    @GET("/api/worker/getGigs")
    suspend fun getGigsWorker(): Response<WorkerGigsResponse>

    @GET("/api/manufacturer/yourGigs")
    suspend fun getGigsManufacturer(): Response<ManufacturerGigResponse>
    @POST("/api/manufacturer/createGig")
    suspend fun createGig(@Body createGigRequest: CreateGigRequest): Response<CreateGigResponse>

    @POST("/api/startup/createGig")
    suspend fun createGigStartup(@Body createGigRequest: CreateGigRequest): Response<CreateGigResponse>

}