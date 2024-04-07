package com.example.worklink.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.worklink.api.LoginAPI
import com.example.worklink.models.FactorySignUpResponse
import com.example.worklink.models.FactorySignupRequest
import com.example.worklink.models.StartupSignupRequest
import com.example.worklink.models.StartupSignupResponse
import com.example.worklink.models.ToggleMachineAvailability
import com.example.worklink.models.ToggleMachineResponse
import com.example.worklink.models.WorkerSignupRequest
import com.example.worklink.models.WorkerSignupResponse
import com.example.worklink.utils.NetworkResult
import org.json.JSONObject
import javax.inject.Inject

class OnboardingRespository @Inject constructor(private val api : LoginAPI){

    private val _signupResponseLiveData = MutableLiveData<NetworkResult<WorkerSignupResponse>>()
    private val _startupSignupResponseLiveData = MutableLiveData<NetworkResult<StartupSignupResponse>>()
    private val _factorySignupResponseLiveData = MutableLiveData<NetworkResult<FactorySignUpResponse>>()
    private val _ToggleLiveData = MutableLiveData<NetworkResult<ToggleMachineResponse>>()

    val signupResponseLiveData :LiveData<NetworkResult<WorkerSignupResponse>>
        get() = _signupResponseLiveData

    val startupSignupResponseLiveData :LiveData<NetworkResult<StartupSignupResponse>>
        get() = _startupSignupResponseLiveData

    val factorySignupResponseLiveData :LiveData<NetworkResult<FactorySignUpResponse>>
        get() = _factorySignupResponseLiveData

    val ToggleLiveData: LiveData<NetworkResult<ToggleMachineResponse>>
        get() = _ToggleLiveData

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

    suspend fun startupSignup(startupSignupRequest: StartupSignupRequest){
        _startupSignupResponseLiveData.postValue(NetworkResult.Loading())
        val response=api.startupsignUp(startupSignupRequest)
        Log.d("TAG", response.body().toString())
        if (response.isSuccessful && response.body() != null) {
            _startupSignupResponseLiveData.postValue(NetworkResult.Success(response.body()))
        } else if (response.errorBody() != null) {
            val errObj = JSONObject(response.errorBody()!!.charStream().readText())
            _startupSignupResponseLiveData.postValue(NetworkResult.Error(errObj.getString("message")))
        } else {
            _startupSignupResponseLiveData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }

    suspend fun factorySignup(factorySignupRequest: FactorySignupRequest){
        _factorySignupResponseLiveData.postValue(NetworkResult.Loading())
        val response=api.manufacturerSignup(factorySignupRequest)
        Log.d("TAG", response.body().toString())
        if (response.isSuccessful && response.body() != null) {
            _factorySignupResponseLiveData.postValue(NetworkResult.Success(response.body()))
        } else if (response.errorBody() != null) {
            val errObj = JSONObject(response.errorBody()!!.charStream().readText())
            _factorySignupResponseLiveData.postValue(NetworkResult.Error(errObj.getString("message")))
        } else {
            _factorySignupResponseLiveData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }

//    suspend fun ToggleMachine(toggleMachineAvailability: ToggleMachineAvailability){
//        _ToggleLiveData.postValue(NetworkResult.Loading())
////        //val response=api.isAvailable()
////        Log.d("TAG", response.body().toString())
////        if (response.isSuccessful && response.body() != null) {
////            _ToggleLiveData.postValue(NetworkResult.Success(response.body()))
////        } else if (response.errorBody() != null) {
////            val errObj = JSONObject(response.errorBody()!!.charStream().readText())
//            _ToggleLiveData.postValue(NetworkResult.Error(errObj.getString("message")))
//        } else {
//            _ToggleLiveData.postValue(NetworkResult.Error("Something went wrong"))
//        }
  //  }
}