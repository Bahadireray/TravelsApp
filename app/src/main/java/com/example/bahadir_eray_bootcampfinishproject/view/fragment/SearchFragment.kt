package com.example.bahadir_eray_bootcampfinishproject.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bahadir_eray_bootcampfinishproject.adapter.NearbyRecyclerView
import com.example.bahadir_eray_bootcampfinishproject.adapter.TopDestinationsAdapter
import com.example.bahadir_eray_bootcampfinishproject.data.model.country.CountryModel
import com.example.bahadir_eray_bootcampfinishproject.databinding.FragmentSearchBinding
import com.example.bahadir_eray_bootcampfinishproject.viewmodel.SearchViewModel


class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: SearchViewModel
    private var countriesModel: ArrayList<CountryModel>? = null
    private var countriesAdapter = TopDestinationsAdapter(arrayListOf())
    private var nearbyRecyclerAdapter: NearbyRecyclerView? = null


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
        viewModel.refreshDataTop()

        observeLiveData()
        /*    binding.topRecyclerView.apply {
                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                adapter = countriesAdapter
            }

     */
        binding.topRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.topRecyclerView.adapter = countriesAdapter

    }

    private fun observeLiveData() {
        viewModel.countriesModel.observe(viewLifecycleOwner, Observer { countries ->
            countries?.let {

                binding.topRecyclerView.visibility = View.VISIBLE
                countriesAdapter.updateCountriesList(countries)

                Log.d("TEST", countries.toString())

            }
        })
    }

}