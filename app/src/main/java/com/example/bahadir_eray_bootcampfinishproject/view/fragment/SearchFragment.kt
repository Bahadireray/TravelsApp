package com.example.bahadir_eray_bootcampfinishproject.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bahadir_eray_bootcampfinishproject.R
import com.example.bahadir_eray_bootcampfinishproject.adapter.NearbyAdapter
import com.example.bahadir_eray_bootcampfinishproject.adapter.TopDestinationsAdapter
import com.example.bahadir_eray_bootcampfinishproject.data.model.travel.TravelsModel
import com.example.bahadir_eray_bootcampfinishproject.databinding.FragmentSearchBinding
import com.example.bahadir_eray_bootcampfinishproject.viewmodel.SearchViewModel
import java.util.*

class SearchFragment : Fragment(), TopDestinationsAdapter.Listener, NearbyAdapter.Listener {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: SearchViewModel
    var disPlayList = ArrayList<TravelsModel>()
    private lateinit var travelList: List<TravelsModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE or WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        _binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this)[SearchViewModel::class.java]
        viewModel.getDataFromAPI()
        observeLiveDataTopDestinations()
        observeLiveDataNearBy()

        binding.searchEditText.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                observeLiveSearchData()
                if (newText!!.isNotEmpty()) {
                    disPlayList.clear()
                    val search = newText.toLowerCase(Locale.getDefault())
                    travelList.forEach {
                        if (it.title.toLowerCase(Locale.getDefault()).contains(search)) {
                            disPlayList.add(it)
                        }
                    }
                    binding.ToptxtView.text = "All Search"
                } else {
                    disPlayList.clear()
                    disPlayList.addAll(travelList)
                    binding.ToptxtView.text = "TOP DESTINATIONS"
                }
                return true
            }
        })
    }

    private fun observeLiveSearchData() {
        viewModel.travelsModel.observe(viewLifecycleOwner, Observer { travels ->
            travels?.let {
                viewModel.setFilter("all")
                travelList = viewModel.filtrelTravelsModel.value!!.toMutableList()
                binding.topRecyclerView.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                binding.topRecyclerView.adapter =
                    TopDestinationsAdapter(disPlayList, this@SearchFragment)
            }
        })
    }

    private fun observeLiveDataTopDestinations() {
        viewModel.travelsModel.observe(viewLifecycleOwner, Observer { travels ->
            travels?.let {
                viewModel.setFilter("topdestination")
                binding.topRecyclerView.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                binding.topRecyclerView.adapter =
                    TopDestinationsAdapter(
                        viewModel.filtrelTravelsModel.value!!.toMutableList(),
                        this
                    )
            }
        })
    }

    private fun observeLiveDataNearBy() {
        viewModel.travelsModel.observe(viewLifecycleOwner, Observer { travels ->
            travels?.let {
                viewModel.setFilter("all")
                binding.nearbyRecyclerView.layoutManager =
                    LinearLayoutManager(context)
                binding.nearbyRecyclerView.adapter =
                    NearbyAdapter(viewModel.filtrelTravelsModel.value!!.toMutableList(), this)
            }
        })
    }

    override fun onItemTopClick(travelsModel: TravelsModel) {
        view?.let {
            val fragment = DetailFragment()
            val bundle = Bundle()
            bundle.putString("travelsCity", travelsModel.city)
            bundle.putString("travelsImg", travelsModel.images?.first()?.url)
            bundle.putString("travelsTitle", travelsModel.title)
            bundle.putString("travelsDescription", travelsModel.description)
            fragment.arguments = bundle
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragmentContainerView, fragment)?.commit()
        }
    }

    override fun onItemNearbyClick(travelsModel: TravelsModel) {
        view?.let {
            val fragment = DetailFragment()
            val bundle = Bundle()
            bundle.putString("travelsCity", travelsModel.city)
            bundle.putString("travelsImg", travelsModel.images?.first()?.url)
            bundle.putString("travelsTitle", travelsModel.title)
            bundle.putString("travelsDescription", travelsModel.description)
            fragment.arguments = bundle
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragmentContainerView, fragment)?.commit()
        }
    }
}