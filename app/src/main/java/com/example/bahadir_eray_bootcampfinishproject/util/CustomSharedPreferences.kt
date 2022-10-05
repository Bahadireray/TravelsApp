package com.example.bahadir_eray_bootcampfinishproject.util

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class CustomSharedPreferences {
    companion object {
        private var sharedPreferences: SharedPreferences? = null
        private val UUID = "UUID"

        @Volatile
        private var instance: CustomSharedPreferences? = null
        private val lock = Any()
        operator fun invoke(context: Context): CustomSharedPreferences =
            instance ?: synchronized(lock) {
                instance ?: makeCustomSharedPreferences(context).also {
                    instance = it
                }
            }

        private fun makeCustomSharedPreferences(context: Context): CustomSharedPreferences {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            return CustomSharedPreferences()
        }
    }

    fun saveTime(uuid: String) {
        sharedPreferences?.edit()?.putInt(uuid.toString(), 0)?.apply()
    }

    fun getTime() = sharedPreferences?.getLong(UUID, 0)
}