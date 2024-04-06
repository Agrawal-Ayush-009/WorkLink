package com.example.worklink.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.worklink.api.MainAPI
import com.example.worklink.models.WorkerGigsResponse
import com.example.worklink.utils.NetworkResult
import org.json.JSONObject
import javax.inject.Inject

class MainRepository @Inject constructor(private val api: MainAPI) {
    private val _workerGigLiveData = MutableLiveData<NetworkResult<WorkerGigsResponse>>()
    val workerGigLiveData: LiveData<NetworkResult<WorkerGigsResponse>> get() = _workerGigLiveData

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