package com.example.bahadir_eray_bootcampfinishproject.view.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bahadir_eray_bootcampfinishproject.adapter.DealsAdapter
import com.example.bahadir_eray_bootcampfinishproject.databinding.FragmentHomeBinding
import com.example.bahadir_eray_bootcampfinishproject.viewmodel.HomeViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel
    var allData = 1


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
        viewModel.getDataFromAPI()
        observeLiveData()
        getAll()

        binding.allTxtView.setOnClickListener {
            binding.allTxtView.setTextColor(Color.parseColor("#ff0000"))
            binding.flighsTxtView.setTextColor(Color.parseColor("#C2C5D6"))
            binding.hotelsTxtView.setTextColor(Color.parseColor("#C2C5D6"))
            binding.transportationsTxtView.setTextColor(Color.parseColor("#C2C5D6"))
            viewModel.setFilter("all")
            getAdapter()
        }

        binding.flighsTxtView.setOnClickListener {
            binding.flighsTxtView.setTextColor(Color.parseColor("#ff0000"))
            binding.allTxtView.setTextColor(Color.parseColor("#C2C5D6"))
            binding.hotelsTxtView.setTextColor(Color.parseColor("#C2C5D6"))
            binding.transportationsTxtView.setTextColor(Color.parseColor("#C2C5D6"))
            viewModel.setFilter("flight")
            getAdapter()

        }
        binding.hotelsTxtView.setOnClickListener {
            binding.flighsTxtView.setTextColor(Color.parseColor("#C2C5D6"))
            binding.allTxtView.setTextColor(Color.parseColor("#C2C5D6"))
            binding.hotelsTxtView.setTextColor(Color.parseColor("#ff0000"))
            binding.transportationsTxtView.setTextColor(Color.parseColor("#C2C5D6"))
            viewModel.setFilter("hotel")
            getAdapter()

        }

        binding.transportationsTxtView.setOnClickListener {

            binding.flighsTxtView.setTextColor(Color.parseColor("#C2C5D6"))
            binding.allTxtView.setTextColor(Color.parseColor("#C2C5D6"))
            binding.hotelsTxtView.setTextColor(Color.parseColor("#C2C5D6"))
            binding.transportationsTxtView.setTextColor(Color.parseColor("#ff0000"))
            viewModel.setFilter("transportation")
            getAdapter()
        }

    }

    private fun getAdapter() {
        binding.dealsRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.dealsRecyclerView.adapter =
            DealsAdapter(viewModel.filtrelHotelsModel.value!!.toMutableList())

    }

    private fun observeLiveData() {
        viewModel.hotelsModel.observe(viewLifecycleOwner, Observer { hotels ->
            hotels?.let {
                viewModel.setFilter("all")
                binding.dealsRecyclerView.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                binding.dealsRecyclerView.adapter =
                    DealsAdapter(viewModel.filtrelHotelsModel.value!!.toMutableList())
            }

        })
    }

    fun getAll() {
        binding.allTxtView.setTextColor(Color.parseColor("#ff0000"))
        binding.flighsTxtView.setTextColor(Color.parseColor("#C2C5D6"))
        binding.hotelsTxtView.setTextColor(Color.parseColor("#C2C5D6"))
        binding.transportationsTxtView.setTextColor(Color.parseColor("#C2C5D6"))
    }
}