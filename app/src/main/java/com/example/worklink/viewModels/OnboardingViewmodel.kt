package com.example.worklink.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.worklink.models.FactorySignupRequest
import com.example.worklink.models.StartupSignupRequest
import com.example.worklink.models.StartupSignupResponse
import com.example.worklink.models.WorkerSignupRequest
import com.example.worklink.repository.OnboardingRespository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewmodel @Inject constructor(private val repository: OnboardingRespository): ViewModel() {

    val signupLiveData = repository.signupResponseLiveData
    val workerSignupLiveData=repository.startupSignupResponseLiveData
    val factorySignupLiveData=repository.factorySignupResponseLiveData

    fun signup(workerSignupRequest: WorkerSignupRequest){
        viewModelScope.launch {
            repository.workerSignup(workerSignupRequest)
        }
    }
    fun startupSignup(startupSignupRequest: StartupSignupRequest){
        viewModelScope.launch {
            repository.startupSignup(startupSignupRequest)
        }
    }
    fun factorySignup(factorySignupRequest: FactorySignupRequest){
        viewModelScope.launch {
            repository.factorySignup(factorySignupRequest)
        }
    }
}