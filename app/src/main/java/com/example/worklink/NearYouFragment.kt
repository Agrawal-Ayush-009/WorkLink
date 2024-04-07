package com.example.worklink

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.worklink.adapters.ManufacturerGigRVAdapter
import com.example.worklink.adapters.WorkerGigRVAdapter
import com.example.worklink.databinding.FragmentNearYouBinding
import com.example.worklink.models.Gig
import com.example.worklink.models.Location
import com.example.worklink.models.ManufacturerGigResponse
import com.example.worklink.utils.NetworkResult
import com.example.worklink.utils.TokenManager
import com.example.worklink.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NearYouFragment : Fragment() {
    private var _binding: FragmentNearYouBinding? = null

    private val binding get() = _binding!!

    @Inject
    lateinit var tokenManager: TokenManager

    val viewmodel by viewModels<MainViewModel>()

    lateinit var adapter: ManufacturerGigRVAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentNearYouBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val role = tokenManager.getRole()

        adapter = ManufacturerGigRVAdapter(
            requireContext(), listOf(
                Gig(
                    69, "hi", listOf("ien"),
                    "iwoen", "2od", Location("je", "2oen"),
                    90, listOf("2eoifn"), 100
                )
            )
        )

        if (role == "Factory") {
            viewmodel.getManufacturerGig()
        }

        bindObserver()
    }

    fun bindObserver() {
        viewmodel.manuGigLiveData.observe(viewLifecycleOwner, Observer {
            binding.progressBar.isVisible = false
            when (it) {
                is NetworkResult.Success -> {
                    val role = tokenManager.getRole().toString()
                    val list = it.data!!.gigs
                    if(list.size == 0){
                        binding.endListText.isVisible = true
                    }else{
                        binding.endListText.isVisible = false
                        adapter = ManufacturerGigRVAdapter(requireContext(), list)
                        val recyclerView = binding.recyclerView
                        recyclerView.layoutManager = LinearLayoutManager(requireContext())
                        recyclerView.adapter = adapter
                    }


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