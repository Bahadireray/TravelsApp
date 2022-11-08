package com.example.bahadir_eray_bootcampfinishproject.view.activity

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.work.*
import com.example.bahadir_eray_bootcampfinishproject.R
import com.example.bahadir_eray_bootcampfinishproject.databinding.ActivityMainBinding
import com.example.bahadir_eray_bootcampfinishproject.databinding.FragmentHomeBinding
import com.example.bahadir_eray_bootcampfinishproject.util.Notification
import com.example.bahadir_eray_bootcampfinishproject.view.fragment.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var binding: ActivityMainBinding
    val fragment = DetailFragment()

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

    private fun travelsTimer() {
        val constraints = Constraints.Builder()
            .setRequiresCharging(false)
            .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
            .setRequiresCharging(false)
            .setRequiresBatteryNotLow(true)
            .build()
        val myRequest = PeriodicWorkRequest.Builder(
            Notification::class.java,
            15,
            TimeUnit.MINUTES
        ).setConstraints(constraints)
            .build()
        WorkManager.getInstance(this)
            .enqueue(myRequest)
    }

    override fun onStop() {
        super.onStop()
        travelsTimer()
    }
}