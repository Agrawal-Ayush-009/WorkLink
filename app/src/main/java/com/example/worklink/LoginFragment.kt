package com.example.worklink

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.worklink.databinding.FragmentLoginBinding
import com.example.worklink.utils.TokenManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding : FragmentLoginBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var tokenManager: TokenManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        Log.d("TAG", tokenManager.getToken().toString())
        Log.d("TAG", tokenManager.getRole().toString())

        if(tokenManager.getToken() != null){
            startActivity(Intent(requireContext(), MainActivity::class.java))
            (activity as LoginActivity).finish()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registerButton.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_onboardFragment)
        }
        binding.loginButton.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_signInFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}