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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.worklink.adapters.WorkerGigRVAdapter
import com.example.worklink.databinding.FragmentAllBinding
import com.example.worklink.utils.NetworkResult
import com.example.worklink.utils.TokenManager
import com.example.worklink.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.math.log

@AndroidEntryPoint
class AllFragment : Fragment() {
    private var _binding : FragmentAllBinding? = null
    private val binding get() = _binding!!

    val viewModel by viewModels<MainViewModel>()

    @Inject
    lateinit var tokenManager: TokenManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentAllBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Frag", tokenManager.getToken().toString())
        viewModel.getWorkerGig()
        bindObserver()
    }

    fun bindObserver(){
        viewModel.workerGigLiveData.observe(viewLifecycleOwner, Observer {
            binding.progressBar.isVisible = false
            when (it) {
                is NetworkResult.Success -> {
                    val list = it.data!!.gigsToShow
                    val adapter = WorkerGigRVAdapter(requireContext(), list)
                    val recyclerView = binding.recyclerView
                    recyclerView.layoutManager = LinearLayoutManager(requireContext())
                    recyclerView.adapter = adapter
                }

                is NetworkResult.Error -> {
                    Toast.makeText(activity as MainActivity, it.msg, Toast.LENGTH_SHORT).show()
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