package com.example.bahadir_eray_bootcampfinishproject.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.bahadir_eray_bootcampfinishproject.R
import com.example.bahadir_eray_bootcampfinishproject.adapter.SeeAllAdapter
import com.example.bahadir_eray_bootcampfinishproject.data.model.travel.TravelsModel
import com.example.bahadir_eray_bootcampfinishproject.databinding.FragmentSeeAllBinding
import com.example.bahadir_eray_bootcampfinishproject.viewmodel.SeeAllViewModel

class SeeAllFragment : Fragment(), SeeAllAdapter.Listener {
    private var _binding: FragmentSeeAllBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: SeeAllViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSeeAllBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(SeeAllViewModel::class.java)
        viewModel.getDataFromAPI()
        observeLiveData()

    }

    private fun observeLiveData() {
        viewModel.travelsModel.observe(viewLifecycleOwner, Observer { travels ->
            travels?.let {
                viewModel.setFilter("all")
                binding.seeAllRecyclerView
                    .layoutManager =
                    StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
                binding.seeAllRecyclerView.adapter =
                    SeeAllAdapter(viewModel.filtrelTravelsModel.value!!.toMutableList(), this)
            }
        })
    }

    override fun onItemClick(travelsModel: TravelsModel) {
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