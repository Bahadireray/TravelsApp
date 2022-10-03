package com.example.bahadir_eray_bootcampfinishproject.view.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bahadir_eray_bootcampfinishproject.adapter.DealsAdapter
import com.example.bahadir_eray_bootcampfinishproject.databinding.FragmentHomeBinding
import com.example.bahadir_eray_bootcampfinishproject.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        viewModel.refreshDataAll()
        binding.dealsRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.allTxtView.setOnClickListener {
            binding.allTxtView.setTextColor(Color.parseColor("#ff0000"))
            viewModel.refreshDataAll()
            viewModel.setFilter("all")
            binding.dealsRecyclerView.adapter =
                DealsAdapter(viewModel.filtrelHotelsModel.value!!.toMutableList())
            observeLiveData()
        }
        binding.flighsTxtView.setOnClickListener {

            binding.flighsTxtView.setTextColor(Color.parseColor("#ff0000"))
            viewModel.setFilter("flight")
            binding.dealsRecyclerView.adapter =
                DealsAdapter(viewModel.filtrelHotelsModel.value!!.toMutableList())
        }
        binding.hotelsTxtView.setOnClickListener {
            viewModel.setFilter("hotel")
            binding.dealsRecyclerView.adapter =
                DealsAdapter(viewModel.filtrelHotelsModel.value!!.toMutableList())
        }
        binding.transportationsTxtView.setOnClickListener {
            viewModel.setFilter("transportation")
            binding.dealsRecyclerView.adapter =
                DealsAdapter(viewModel.filtrelHotelsModel.value!!.toMutableList())
        }
    }

    private fun observeLiveData() {

    }

}