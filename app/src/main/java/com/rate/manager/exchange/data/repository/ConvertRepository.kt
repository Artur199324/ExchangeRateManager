package com.rate.manager.exchange.data.repository

import android.util.Log
import com.rate.manager.exchange.domain.models.ConvertModels
import com.rate.manager.exchange.domain.repository.ConvertRepositoryInterface
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class ConvertRepository : ConvertRepositoryInterface {
    var resamount: String = ""
    var fromamount: String = ""
    var toamount: String = ""
    override suspend fun convert(from: String, to: String, amount: String): ConvertModels {

        try {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://currency-converter-pro1.p.rapidapi.com/convert?from=$from&to=$to&amount=$amount")
            .get()
            .addHeader("X-RapidAPI-Key", "85910b09eamshdfee78236f66566p13229ajsn51e9ae1d810f")
            .addHeader("X-RapidAPI-Host", "currency-converter-pro1.p.rapidapi.com")
            .build()

        val response = client.newCall(request).execute()
        val jsonObject = response.body?.string()?.let { JSONObject(it) }
        resamount = jsonObject?.getString("result").toString()
        val jsonA = jsonObject?.getJSONObject("meta")
        val jsonb = jsonA?.getJSONObject("formated")
        fromamount = jsonb?.getString("from").toString()
        toamount = jsonb?.getString("to").toString()

        }catch (e:Exception){
            e.message
        }


        return ConvertModels(resamount, fromamount, toamount)
    }


}