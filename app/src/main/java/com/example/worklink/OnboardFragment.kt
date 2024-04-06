package com.example.worklink

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.fragment.findNavController
import com.example.worklink.databinding.FragmentOnboardBinding

class OnboardFragment : Fragment() {
    private var _binding: FragmentOnboardBinding? = null
    private val binding get() = _binding!!
    private var clickedButton = arrayListOf<AppCompatButton>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentOnboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val worker = binding.worker
        val manufacturer = binding.factory
        val startup = binding.startup

        worker.setOnClickListener {
            clickedButton.add(worker)
            worker.setTextColor(Color.parseColor("#000000"))
            manufacturer.setTextColor(Color.parseColor("#9C9C9C"))
            startup.setTextColor(Color.parseColor("#9C9C9C"))
            worker.setBackgroundDrawable(resources.getDrawable(R.drawable.clicked_button))
            manufacturer.setBackgroundDrawable(resources.getDrawable(R.drawable.button_option))
            startup.setBackgroundDrawable(resources.getDrawable(R.drawable.button_option))
        }

        manufacturer.setOnClickListener {
            clickedButton.add(manufacturer)
            manufacturer.setTextColor(Color.parseColor("#000000"))
            worker.setTextColor(Color.parseColor("#9C9C9C"))
            startup.setTextColor(Color.parseColor("#9C9C9C"))
            manufacturer.setBackgroundDrawable(resources.getDrawable(R.drawable.clicked_button))
            startup.setBackgroundDrawable(resources.getDrawable(R.drawable.button_option))
            worker.setBackgroundDrawable(resources.getDrawable(R.drawable.button_option))
        }
        startup.setOnClickListener {
            clickedButton.add(startup)
            startup.setTextColor(Color.parseColor("#000000"))
            manufacturer.setTextColor(Color.parseColor("#9C9C9C"))
            worker.setTextColor(Color.parseColor("#9C9C9C"))
            startup.setBackgroundDrawable(resources.getDrawable(R.drawable.clicked_button))
            manufacturer.setBackgroundDrawable(resources.getDrawable(R.drawable.button_option))
            worker.setBackgroundDrawable(resources.getDrawable(R.drawable.button_option))
        }

        binding.getStarted.setOnClickListener {
            if(clickedButton.isEmpty()){
                Toast.makeText(requireContext(), "Please Select an Option", Toast.LENGTH_SHORT).show()
            }else{
                val button = clickedButton[clickedButton.lastIndex]
                (activity as LoginActivity).role = button.text.toString()
                findNavController().navigate(R.id.action_onboardFragment_to_registerFragment)
            }

        }


    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        clickedButton.clear()
    }
}