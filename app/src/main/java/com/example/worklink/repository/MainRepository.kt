package com.example.worklink.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.worklink.api.MainAPI
import com.example.worklink.models.ToggleMachineResponse
import com.example.worklink.models.CreateGigRequest
import com.example.worklink.models.CreateGigResponse
import com.example.worklink.models.ManufacturerGigResponse
import com.example.worklink.models.WorkerGigsResponse
import com.example.worklink.utils.NetworkResult
import org.json.JSONObject
import javax.inject.Inject

class MainRepository @Inject constructor(private val api: MainAPI) {
    private val _workerGigLiveData = MutableLiveData<NetworkResult<WorkerGigsResponse>>()
    val workerGigLiveData: LiveData<NetworkResult<WorkerGigsResponse>> get() = _workerGigLiveData
    private val _createGigLiveData = MutableLiveData<NetworkResult<CreateGigResponse>>()
    val createGigLiveData : LiveData<NetworkResult<CreateGigResponse>> get() = _createGigLiveData

    private val _manGigLiveData = MutableLiveData<NetworkResult<ManufacturerGigResponse>>()
    val manGigLiveData : LiveData<NetworkResult<ManufacturerGigResponse>> get() = _manGigLiveData

    suspend fun getManufacturerGig(){
        _manGigLiveData.postValue(NetworkResult.Loading())
        val response = api.getGigsManufacturer()
        Log.d("repo", response.body().toString())
        if (response.isSuccessful && response.body() != null) {
            _manGigLiveData.postValue(NetworkResult.Success(response.body()))
        } else if (response.errorBody() != null) {
            val errObj = JSONObject(response.errorBody()!!.charStream().readText())
            _manGigLiveData.postValue(NetworkResult.Error(errObj.getString("message")))
        } else {
            _manGigLiveData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }


    suspend fun createGig(createGigRequest: CreateGigRequest){
        _createGigLiveData.postValue(NetworkResult.Loading())
        val response = api.createGig(createGigRequest)
        Log.d("repo", response.body().toString())
        if (response.isSuccessful && response.body() != null) {
            _createGigLiveData.postValue(NetworkResult.Success(response.body()))
        } else if (response.errorBody() != null) {
            val errObj = JSONObject(response.errorBody()!!.charStream().readText())
            _createGigLiveData.postValue(NetworkResult.Error(errObj.getString("message")))
        } else {
            _createGigLiveData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }


    suspend fun createGigStartup(createGigRequest: CreateGigRequest){
        _createGigLiveData.postValue(NetworkResult.Loading())
        val response = api.createGigStartup(createGigRequest)
        Log.d("repo", response.body().toString())
        if (response.isSuccessful && response.body() != null) {
            _createGigLiveData.postValue(NetworkResult.Success(response.body()))
        } else if (response.errorBody() != null) {
            val errObj = JSONObject(response.errorBody()!!.charStream().readText())
            _createGigLiveData.postValue(NetworkResult.Error(errObj.getString("message")))
        } else {
            _createGigLiveData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }


    suspend fun getWorkerGig(){
        _workerGigLiveData.postValue(NetworkResult.Loading())
        val response = api.getGigsWorker()
        Log.d("repo", response.body().toString())
        if (response.isSuccessful && response.body() != null) {
            _workerGigLiveData.postValue(NetworkResult.Success(response.body()))
        } else if (response.errorBody() != null) {
            val errObj = JSONObject(response.errorBody()!!.charStream().readText())
            _workerGigLiveData.postValue(NetworkResult.Error(errObj.getString("message")))
        } else {
            _workerGigLiveData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }
}