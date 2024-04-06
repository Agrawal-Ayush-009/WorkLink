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
import com.example.worklink.databinding.FragmentMachineryBinding
import com.example.worklink.models.FactorySignupRequest
import com.example.worklink.models.Location
import com.example.worklink.models.Machine
import com.example.worklink.utils.NetworkResult
import com.example.worklink.utils.TokenManager
import com.example.worklink.viewModels.OnboardingViewmodel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class MachineryFragment : Fragment() {
    private var _binding : FragmentMachineryBinding? = null
    private val binding get() = _binding!!
    val authViewmodel by viewModels<OnboardingViewmodel>()

    @Inject
    lateinit var tokenManager : TokenManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentMachineryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.register.setOnClickListener {

            val activity = activity as LoginActivity
            val machineName1=_binding!!.machine1Name.text.toString()
            val machineName2=_binding!!.machine2Name.text.toString()
            val machine1Time=_binding!!.machine1Time.text.toString().toInt()
            val machine2Time=_binding!!.machine2Time.text.toString()
            val name=activity.factoryName
            val work=activity.factoryWork
            val city=activity.factoryCity
            val state=activity.factoryState
            val email=activity.email
            val password=activity.password
            val machine = listOf(Machine(machineName1,true,machine1Time))
            val gig = listOf("gig1", "gig2")



            Log.e("id123","$name")
            Log.e("id123","$machineName1")
            Log.e("id123","$machineName2")
            Log.e("id123","$work")
            Log.e("id123","$city")
            Log.e("id123","$state")
            Log.e("id123","$email")
            Log.e("id123","$password")
            authViewmodel.factorySignup(FactorySignupRequest(name, email,password,work,machine,
                Location(city,state),"https://i.postimg.cc/c15MbgrZ/pngwing-com.png", gig
            ))

            bindObserver()
        }
    }

    private fun bindObserver() {
        authViewmodel.factorySignupLiveData.observe(viewLifecycleOwner, Observer {
            binding.progressBar.isVisible = false
            when (it) {
                is NetworkResult.Success -> {
                    tokenManager.saveToken(it.data!!.token)
                    tokenManager.saveRole("Factory")
                    Log.d("TAG", tokenManager.getToken().toString())
                    startActivity(Intent(requireContext(), MainActivity::class.java))
                    (activity as LoginActivity).finish()
                }

                is NetworkResult.Error -> {
                    Toast.makeText(requireContext(), it.msg, Toast.LENGTH_SHORT).show()
                    Log.e("id1234","${it.msg}")
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