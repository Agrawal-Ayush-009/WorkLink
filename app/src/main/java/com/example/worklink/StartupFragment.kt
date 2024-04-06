package com.example.worklink

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.worklink.databinding.FragmentStartupBinding
import com.example.worklink.models.Location
import com.example.worklink.models.StartupSignupRequest
import com.example.worklink.utils.NetworkResult
import com.example.worklink.utils.TokenManager
import com.example.worklink.viewModels.OnboardingViewmodel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class StartupFragment : Fragment() {
    private var _binding: FragmentStartupBinding? = null
    private val binding get() = _binding!!
    val authViewmodel by viewModels<OnboardingViewmodel>()
    @Inject
    lateinit var tokenManager : TokenManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentStartupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.regiter.setOnClickListener {
            val activity = activity as LoginActivity
            val name=_binding!!.nameInput.text.toString()
            val workInput=_binding!!.nameInput.text.toString()
            val state=_binding!!.locationInputState.text.toString()
            val city=_binding!!.locationInputCity.text.toString()
            val email = activity.email
            val password = activity.password

            authViewmodel.startupSignup(StartupSignupRequest(name,email,password,workInput,
                Location(city,state),"https://i.postimg.cc/c15MbgrZ/pngwing-com.png"))

            Log.e("id123","$name")
            Log.e("id123","$workInput")
            Log.e("id123","$state")
            Log.e("id123","$city")
            Log.e("id123","$email")
            Log.e("id123","$password")

            bindObserver()

        }
    }
    fun bindObserver(){
        authViewmodel.workerSignupLiveData.observe(viewLifecycleOwner, Observer {
            binding.progressBar.isVisible = false
            when (it) {
                is NetworkResult.Success -> {
                    tokenManager.saveToken(it.data!!.token)
                    tokenManager.saveRole("StartUp")
                    Log.d("TAG", tokenManager.getToken().toString())
                    startActivity(Intent(requireContext(), MainActivity::class.java))
                    (activity as LoginActivity).finish()
                }

                is NetworkResult.Error -> {
                    Toast.makeText(activity as LoginActivity, it.msg, Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }

                is NetworkResult.Loading -> {
                    binding.progressBar.isVisible = true
                }
                else -> {}
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}