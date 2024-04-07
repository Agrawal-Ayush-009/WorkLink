package com.example.worklink.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.worklink.models.CreateGigRequest
import com.example.worklink.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository): ViewModel(){
    val workerGigLiveData = repository.workerGigLiveData
    val createGigLiveData = repository.createGigLiveData
    val manuGigLiveData = repository.manGigLiveData

    fun getManufacturerGig(){
        viewModelScope.launch {
            repository.getManufacturerGig()
        }
    }


    fun createGigStartUp(createGigRequest: CreateGigRequest){
        viewModelScope.launch {
            repository.createGigStartup(createGigRequest)
        }
    }
    fun createGig(createGigRequest: CreateGigRequest){
        viewModelScope.launch {
            repository.createGig(createGigRequest)
        }
    }

    fun getWorkerGig(){
        viewModelScope.launch {
            repository.getWorkerGig()
        }
    }
}