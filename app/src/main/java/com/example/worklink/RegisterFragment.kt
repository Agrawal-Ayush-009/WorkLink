package com.example.worklink

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.worklink.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registerButton.setOnClickListener {
            val activity = activity as LoginActivity
            val email = binding.emailInput.text.toString()
            val password =  binding.passwordInput.text.toString()
            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(requireContext(), "Both fields are required", Toast.LENGTH_SHORT).show()
            }else{
                if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    Toast.makeText(requireContext(), "Provide Valid Info", Toast.LENGTH_SHORT).show()
                }else{
                    activity.email = email
                    activity.password = password
                    val role = activity.role
                    when (role) {
                        "Worker" -> {
                            findNavController().navigate(R.id.action_registerFragment_to_workerFragment)
                        }
                        "Factory Owner" -> {
                            findNavController().navigate(R.id.action_registerFragment_to_factoryFragment)
                        }
                        else -> {
                            findNavController().navigate(R.id.action_registerFragment_to_startupFragment)
                        }
                    }
                }
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}