package com.example.worklink.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.worklink.models.WorkerSignupRequest
import com.example.worklink.repository.OnboardingRespository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewmodel @Inject constructor(private val repository: OnboardingRespository): ViewModel() {

    val signupLiveData = repository.signupResponseLiveData

    fun signup(workerSignupRequest: WorkerSignupRequest){
        viewModelScope.launch {
            repository.workerSignup(workerSignupRequest)
        }
    }
}