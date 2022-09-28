package com.example.bahadir_eray_bootcampfinishproject.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bahadir_eray_bootcampfinishproject.adapter.HotelRecyclerAdapter
import com.example.bahadir_eray_bootcampfinishproject.data.model.HotelsModel
import com.example.bahadir_eray_bootcampfinishproject.databinding.FragmentHomeBinding
import com.example.bahadir_eray_bootcampfinishproject.service.HotelsService
import com.example.bahadir_eray_bootcampfinishproject.viewmodel.HomeViewModel

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: HomeViewModel
    private var hotelModels: ArrayList<HotelsModel>? = null
    private var hotelsAdapter = HotelRecyclerAdapter(arrayListOf())
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private var recyclerViewAdapter: HotelRecyclerAdapter? = null


    private val hotelsService = HotelsService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        viewModel.getDataFromAPI()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

}