package com.rate.manager.exchange.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.rate.manager.exchange.domain.repository.SaveInterface

class SaveRepository(val context: Context):SaveInterface {
    var sharedPreferences:SharedPreferences =
        context.getSharedPreferences(context.packageName,Context.MODE_PRIVATE)

    override fun setData(s: String) {
        sharedPreferences.edit().putString(context.packageName,s).apply()
    }

    override fun getData(): String {
        return sharedPreferences.getString(context.packageName,"").toString()
    }
}