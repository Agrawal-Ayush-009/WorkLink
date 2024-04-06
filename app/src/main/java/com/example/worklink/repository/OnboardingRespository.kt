package com.example.worklink.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.worklink.api.LoginAPI
import com.example.worklink.models.WorkerSignupRequest
import com.example.worklink.models.WorkerSignupResponse
import com.example.worklink.utils.NetworkResult
import org.json.JSONObject
import javax.inject.Inject

class OnboardingRespository @Inject constructor(private val api : LoginAPI){

    private val _signupResponseLiveData = MutableLiveData<NetworkResult<WorkerSignupResponse>>()
    val signupResponseLiveData :LiveData<NetworkResult<WorkerSignupResponse>>
        get() = _signupResponseLiveData

    suspend fun workerSignup(workerSignupRequest: WorkerSignupRequest){
        _signupResponseLiveData.postValue(NetworkResult.Loading())
        val response = api.workerSigup(workerSignupRequest)
        Log.d("TAG", response.body().toString())
        if (response.isSuccessful && response.body() != null) {
            _signupResponseLiveData.postValue(NetworkResult.Success(response.body()))
        } else if (response.errorBody() != null) {
            val errObj = JSONObject(response.errorBody()!!.charStream().readText())
            _signupResponseLiveData.postValue(NetworkResult.Error(errObj.getString("message")))
        } else {
            _signupResponseLiveData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }

}