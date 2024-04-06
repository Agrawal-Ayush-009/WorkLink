package com.example.worklink

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.worklink.databinding.FragmentFactoryBinding

class FactoryFragment : Fragment() {
    private var _binding: FragmentFactoryBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentFactoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.next.setOnClickListener {
            findNavController().navigate(R.id.action_factoryFragment_to_machineryFragment2)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}