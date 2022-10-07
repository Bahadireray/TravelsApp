package com.example.bahadir_eray_bootcampfinishproject.view.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.bahadir_eray_bootcampfinishproject.R
import com.example.bahadir_eray_bootcampfinishproject.adapter.BookmarkAdapter
import com.example.bahadir_eray_bootcampfinishproject.data.model.favori.FavoriModel
import com.example.bahadir_eray_bootcampfinishproject.data.roomdb.FavoriDao
import com.example.bahadir_eray_bootcampfinishproject.data.roomdb.FavoriDataBase
import com.example.bahadir_eray_bootcampfinishproject.databinding.FragmentTripBinding
import com.example.bahadir_eray_bootcampfinishproject.viewmodel.TripViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class TripFragment : Fragment(), BookmarkAdapter.Listener {
    private var _binding: FragmentTripBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: TripViewModel
    private lateinit var favoriDao: FavoriDao
    private lateinit var favoriDataBase: FavoriDataBase
    private val mDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTripBinding.inflate(layoutInflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(TripViewModel::class.java)
        favoriDataBase =
            Room.databaseBuilder(requireContext(), FavoriDataBase::class.java, "Favories")
                .allowMainThreadQueries()
                .build()
        favoriDao = favoriDataBase.favoriDao()
        binding.bookmarkRecyclerView.visibility = View.GONE
        binding.bookmarLinearLayout.setOnClickListener {
            getViewBookMark()
            getDataSql()
        }
        binding.tripLinearLayout.setOnClickListener {
            getViewTrips()
        }
        binding.addMarkTrips.setOnClickListener {
            val fragment = SearchFragment()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragmentContainerView, fragment)?.commit()
        }
    }

    fun getViewTrips() {
        binding.bookmarkText.setTextColor(Color.parseColor("#808080"))
        binding.tripsText.setTextColor(Color.parseColor("#ff0000"))
        binding.tripsImg.setImageResource(R.drawable.trips_red)
        binding.bookmarkImg.setImageResource(R.drawable.addbok)
        binding.tripsListLinear.visibility = View.VISIBLE
        binding.bookmarkRecyclerView.visibility = View.GONE
        binding.addMarkTrips.visibility = View.VISIBLE
    }

    fun getViewBookMark() {
        binding.bookmarkText.setTextColor(Color.parseColor("#ff0000"))
        binding.tripsText.setTextColor(Color.parseColor("#808080"))
        binding.tripsImg.setImageResource(R.drawable.trip)
        binding.bookmarkImg.setImageResource(R.drawable.red_save)
        binding.tripsListLinear.visibility = View.GONE
        binding.bookmarkRecyclerView.visibility = View.VISIBLE
        binding.addMarkTrips.visibility = View.GONE
    }

    fun getDataSql() {
        mDisposable.add(
            favoriDao.getFavoriAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse)
        )
    }

    private fun handleResponse(favoriList: List<FavoriModel>) {
        binding.bookmarkRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.bookmarkRecyclerView.adapter =
            BookmarkAdapter(favoriList, this)
    }

    override fun onItemBookClick(favoriModel: FavoriModel) {
        view?.let {
            val fragment = DetailFragment()
            val bundle = Bundle()
            bundle.putString("save", "save")
            bundle.putString("travelsCity", favoriModel.city)
            bundle.putString("travelsImg", favoriModel.images)
            bundle.putString("travelsTitle", favoriModel.title)
            bundle.putString("travelsDescription", favoriModel.description)
            fragment.arguments = bundle
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.fragmentContainerView, fragment)?.commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mDisposable.clear()
    }
}
