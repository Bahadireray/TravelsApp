package com.example.bahadir_eray_bootcampfinishproject.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bahadir_eray_bootcampfinishproject.R
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
    private var hotelsAdapter = HotelRecyclerAdapter(arrayListOf())
    lateinit var hotelsList: ArrayList<HotelsModel>
    private var recyclerViewAdapter: HotelRecyclerAdapter? = null

    private val hotelsService = HotelsService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
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
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(
            requireContext().applicationContext,
            2,
            GridLayoutManager.VERTICAL,
            false
        )
        binding.dealsRecyclerView.layoutManager = layoutManager
        hotelsList = ArrayList()
        getDataFromAPI()
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        viewModel.hotels
    }

    private fun getDataFromAPI() {
        val call = hotelsService.getHotels()
        call.enqueue(object : Callback<ArrayList<HotelsModel>> {
            override fun onResponse(
                call: Call<ArrayList<HotelsModel>>,
                response: Response<ArrayList<HotelsModel>>
            ) {
                if (response.isSuccessful) {


                    Log.v("Test", response.raw().toString())

                    response.body()?.let {

                        Log.v("Test","sucsess")
                        hotelsList= ArrayList(it)
                        for (hotel : HotelsModel in hotelsList!!)
                        {
                            Log.v("Test",hotel.hotelImages.toString())
                        }
                    }

                   /* hotelsList = response.body()!!
                    recyclerViewAdapter = HotelRecyclerAdapter(hotelsList)
                    binding.dealsRecyclerView.adapter = recyclerViewAdapter

                    */
                }
            }

            override fun onFailure(call: Call<ArrayList<HotelsModel>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}