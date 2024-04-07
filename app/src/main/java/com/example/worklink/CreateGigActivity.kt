package com.example.worklink

import android.os.Bundle
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.worklink.adapters.WorkerGigRVAdapter
import com.example.worklink.models.CreateGigRequest
import com.example.worklink.models.Location
import com.example.worklink.utils.NetworkResult
import com.example.worklink.utils.TokenManager
import com.example.worklink.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CreateGigActivity : AppCompatActivity() {
    val viewModel by viewModels<MainViewModel>()


    lateinit var tokenManager :TokenManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_create_gig)
        tokenManager = TokenManager(this)

        val create = findViewById<AppCompatButton>(R.id.create)

        create.setOnClickListener {
            val city = findViewById<EditText>(R.id.locationInputCity).text.toString()
            val state = findViewById<EditText>(R.id.locationInputState).text.toString()
            val skill = findViewById<EditText>(R.id.workInput).text.toString()
            val pay = findViewById<EditText>(R.id.payInput).text.toString().toInt()
            val workerLimit = findViewById<EditText>(R.id.workerLimit).text.toString().toInt()
            val jobDesc = findViewById<EditText>(R.id.desc_input).text.toString()

            val role = tokenManager.getRole()
            if(role == "Factory"){
                viewModel.createGig(CreateGigRequest(jobDesc, Location(city, state), pay, listOf(skill), workerLimit))
            }else if(role == "StartUp"){
                viewModel.createGigStartUp(CreateGigRequest(jobDesc, Location(city, state), pay, listOf(skill), workerLimit))
            }
        }

        bindObserver()

    }

    fun bindObserver(){
        viewModel.createGigLiveData.observe(this, Observer {
            findViewById<ProgressBar>(R.id.progressBar).isVisible = false
            when (it) {
                is NetworkResult.Success -> {
                    Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show()
                    finish()
                }

                is NetworkResult.Error -> {
                    Toast.makeText(this, it.msg, Toast.LENGTH_SHORT).show()
                }

                is NetworkResult.Loading -> {
                    findViewById<ProgressBar>(R.id.progressBar).isVisible = true
                }

                else -> {}
            }
        })
    }
}