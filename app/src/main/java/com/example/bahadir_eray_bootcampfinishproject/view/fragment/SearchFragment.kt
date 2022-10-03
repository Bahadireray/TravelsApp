package com.example.bahadir_eray_bootcampfinishproject.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bahadir_eray_bootcampfinishproject.adapter.DealsAdapter
import com.example.bahadir_eray_bootcampfinishproject.databinding.FragmentSearchBinding
import com.example.bahadir_eray_bootcampfinishproject.viewmodel.SearchViewModel


class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: SearchViewModel
    var allData = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        viewModel.getDataFromAPI()
        observeLiveDataTopDestinations()
        observeLiveDataNearBy()
    }

    private fun observeLiveDataTopDestinations() {
        viewModel.hotelsModel.observe(viewLifecycleOwner, Observer { hotels ->
            hotels?.let {
                viewModel.setFilter("topdestination")

                binding.topRecyclerView.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                binding.topRecyclerView.adapter =
                    DealsAdapter(viewModel.filtrelHotelsModel.value!!.toMutableList())
            }

        })
    }

    private fun observeLiveDataNearBy() {
        viewModel.hotelsModel.observe(viewLifecycleOwner, Observer { hotels ->
            hotels?.let {
                viewModel.setFilter("nearby")
                binding.nearbyRecyclerView.layoutManager =
                    LinearLayoutManager(context)
                binding.nearbyRecyclerView.adapter =
                    DealsAdapter(viewModel.filtrelHotelsModel.value!!.toMutableList())
            }

        })
    }

}