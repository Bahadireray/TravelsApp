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
import com.example.bahadir_eray_bootcampfinishproject.adapter.CategoriAdapter
import com.example.bahadir_eray_bootcampfinishproject.adapter.MightAdapter
import com.example.bahadir_eray_bootcampfinishproject.adapter.TopPicAdapter
import com.example.bahadir_eray_bootcampfinishproject.data.model.travel.TravelsModel
import com.example.bahadir_eray_bootcampfinishproject.databinding.FragmentGuideBinding
import com.example.bahadir_eray_bootcampfinishproject.viewmodel.GuideViewModel
import java.util.*

class GuideFragment : Fragment(), MightAdapter.Listener, TopPicAdapter.Listener {
    private var _binding: FragmentGuideBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: GuideViewModel
    var disPlayList = ArrayList<TravelsModel>()
    private lateinit var travelList: List<TravelsModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE or WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        _binding = FragmentGuideBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this)[GuideViewModel::class.java]
        viewModel.getDataFromAPI()
        observeLiveDataTopDestinations()
        observeLiveDataMightNeed()
        observeLiveDataCategori()

        binding.seeAllTextView.setOnClickListener {
            val fragment = SeeAllFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragmentContainerView, fragment)?.commit()
        }

        binding.searchText.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

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
                    binding.mightTxtView.text = "All Search"

                } else {
                    disPlayList.clear()
                    disPlayList.addAll(travelList)

                    binding.mightTxtView.text = "Might need these"
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
                binding.mightRecyclerView.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                binding.mightRecyclerView.adapter =
                    MightAdapter(disPlayList, this@GuideFragment)
            }
        })
    }

    private fun observeLiveDataTopDestinations() {
        viewModel.travelsModel.observe(viewLifecycleOwner, Observer { travels ->
            travels?.let {
                viewModel.setFilter("topdestination")
                binding.topPicRecyclerView.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                binding.topPicRecyclerView.adapter =
                    TopPicAdapter(viewModel.filtrelTravelsModel.value!!.toMutableList(), this)
            }
        })
    }

    private fun observeLiveDataMightNeed() {
        viewModel.travelsModel.observe(viewLifecycleOwner, Observer { travels ->
            travels?.let {
                viewModel.setFilter("transportation")
                binding.mightRecyclerView.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                binding.mightRecyclerView.adapter =
                    MightAdapter(viewModel.filtrelTravelsModel.value!!.toMutableList(), this)
            }
        })
    }

    private fun observeLiveDataCategori() {
        viewModel.travelsModel.observe(viewLifecycleOwner, Observer { travels ->
            travels?.let {
                viewModel.setFilter("topdestination")
                binding.categoriRecycler.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                binding.categoriRecycler.adapter =
                    CategoriAdapter(viewModel.filtrelTravelsModel.value!!.toMutableList())
            }
        })
    }

    override fun onItemMightClick(travelsModel: TravelsModel) {
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

    override fun onItemPicClick(travelsModel: TravelsModel) {
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