package com.example.bahadir_eray_bootcampfinishproject.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.example.bahadir_eray_bootcampfinishproject.R
import com.example.bahadir_eray_bootcampfinishproject.databinding.ActivityMainBinding
import com.example.bahadir_eray_bootcampfinishproject.view.fragment.GuideFragment
import com.example.bahadir_eray_bootcampfinishproject.view.fragment.HomeFragment
import com.example.bahadir_eray_bootcampfinishproject.view.fragment.SearchFragment
import com.example.bahadir_eray_bootcampfinishproject.view.fragment.TripFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        replaceFragment(HomeFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.homeFragment -> replaceFragment(HomeFragment())
                R.id.searchFragment -> replaceFragment(SearchFragment())
                R.id.tripFragment -> replaceFragment(TripFragment())
                R.id.guideFragment -> replaceFragment(GuideFragment())
                else -> {
                    replaceFragment(HomeFragment())
                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment)
        fragmentTransaction.commit()
    }
}