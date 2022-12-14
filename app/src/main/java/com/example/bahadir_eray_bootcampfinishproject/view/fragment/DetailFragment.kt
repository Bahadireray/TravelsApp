package com.example.bahadir_eray_bootcampfinishproject.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.bahadir_eray_bootcampfinishproject.data.model.favori.FavoriModel
import com.example.bahadir_eray_bootcampfinishproject.databinding.FragmentDetailBinding
import com.example.bahadir_eray_bootcampfinishproject.util.downloadFromUrl
import com.example.bahadir_eray_bootcampfinishproject.util.placeholderProgressBar
import com.example.bahadir_eray_bootcampfinishproject.viewmodel.DetailViewModel

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this)[DetailViewModel::class.java]
        getDetailData()
        buttonResponse()

        binding.addBookMark.setOnClickListener {
            save()
        }
        binding.addBookMarkSave.setOnClickListener {
            delete()
        }
    }

    private fun buttonResponse() {
        val args = this.arguments
        val inputSave = args?.get("save")
        if (inputSave == "save") {
            binding.addBookMark.visibility = View.GONE
            binding.addBookMarkSave.visibility = View.VISIBLE
        } else {
            binding.addBookMark.visibility = View.VISIBLE
            binding.addBookMarkSave.visibility = View.GONE
        }
    }

    private fun getDetailData() {
        val args = this.arguments
        val inputTitle = args?.get("travelsTitle")
        val inputCity = args?.get("travelsCity")
        val inputImg = args?.get("travelsImg")
        val inputDescription = args?.get("travelsDescription")
        binding.detailCiytText.text = inputCity.toString()
        binding.detailTitleText.text = inputTitle.toString()
        binding.detailDescriptiontext.text = inputDescription.toString()
        binding.detailImgView.downloadFromUrl(
            inputImg.toString(),
            placeholderProgressBar(requireActivity())
        )
        binding.addBookMark.visibility = View.VISIBLE
        binding.addBookMarkSave.visibility = View.GONE
    }

    private fun delete() {
        val args = this.arguments
        val inputTitle = args?.get("travelsTitle")
        val inputCity = args?.get("travelsCity")
        val inputImg = args?.get("travelsImg")
        val inputDescription = args?.get("travelsDescription")
        var favoriModel = FavoriModel(
            inputCity.toString(),
            inputDescription.toString(),
            inputImg.toString(),
            inputTitle.toString()
        )
        viewModel.deleteDataBase(favoriModel)

        binding.addBookMark.visibility = View.VISIBLE
        binding.addBookMarkSave.visibility = View.GONE
    }

    private fun save() {
        val args = this.arguments
        val inputTitle = args?.get("travelsTitle")
        val inputCity = args?.get("travelsCity")
        val inputImg = args?.get("travelsImg")
        val inputDescription = args?.get("travelsDescription")
        val favoriModel = FavoriModel(
            inputCity.toString(),
            inputDescription.toString(),
            inputImg.toString(),
            inputTitle.toString()
        )
        viewModel.saveDatabase(favoriModel)

        binding.addBookMark.visibility = View.GONE
        binding.addBookMarkSave.visibility = View.VISIBLE
    }
}