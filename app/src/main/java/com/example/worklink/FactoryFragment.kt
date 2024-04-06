package com.example.worklink

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.worklink.databinding.FragmentFactoryBinding
import com.example.worklink.utils.TokenManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FactoryFragment : Fragment() {
    private var _binding: FragmentFactoryBinding? = null
    private val binding get() = _binding!!
    @Inject
    lateinit var tokenManager : TokenManager
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
            val activity = activity as LoginActivity
            val name=_binding!!.nameInput.text.toString()
            val work=_binding!!.workInput.text.toString()
            val city=_binding!!.locationInputCity.text.toString()
            val state=binding.locationInputState.text.toString()

            if(name.isEmpty() || work.isEmpty() || city.isEmpty() || state.isEmpty()){
                Toast.makeText(requireContext(), "All fields are required", Toast.LENGTH_SHORT).show()
            }else{
                activity.factoryCity=city
                activity.factoryName=name
                activity.factoryWork=work
                activity.factoryState=state
                findNavController().navigate(R.id.action_factoryFragment_to_machineryFragment2)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}