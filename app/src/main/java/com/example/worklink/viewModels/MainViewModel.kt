package com.example.worklink.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.worklink.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository): ViewModel(){
    val workerGigLiveData = repository.workerGigLiveData

    fun getWorkerGig(){
        viewModelScope.launch {
            repository.getWorkerGig()
        }
    }
}