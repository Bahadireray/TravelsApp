package com.example.bahadir_eray_bootcampfinishproject.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bahadir_eray_bootcampfinishproject.R
import com.example.bahadir_eray_bootcampfinishproject.adapter.DealsAdapter
import com.example.bahadir_eray_bootcampfinishproject.adapter.TopPicRecyclerView
import com.example.bahadir_eray_bootcampfinishproject.databinding.FragmentGuideBinding
import com.example.bahadir_eray_bootcampfinishproject.viewmodel.GuideViewModel
import com.example.bahadir_eray_bootcampfinishproject.viewmodel.SearchViewModel

class GuideFragment : Fragment() {


    private var _binding: FragmentGuideBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: GuideViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGuideBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.seeAllTextView.setOnClickListener {
            //   Navigation.findNavController(view).navigate(R.id.action_guideFragment_to_seeAllFragment)
            Toast.makeText(context, "see All", Toast.LENGTH_SHORT).show()
        }
        viewModel = ViewModelProviders.of(this).get(GuideViewModel::class.java)
        viewModel.getDataFromAPI()
        observeLiveDataTopDestinations()

    }

    private fun observeLiveDataTopDestinations() {
        viewModel.hotelsModel.observe(viewLifecycleOwner, Observer { hotels ->
            hotels?.let {
                viewModel.setFilter("topdestination")

                binding.topPicRecyclerView.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                binding.topPicRecyclerView.adapter =
                    TopPicRecyclerView(viewModel.filtrelHotelsModel.value!!.toMutableList())
            }

        })
    }


}