package com.rate.manager.exchange.data.repository

import com.rate.manager.exchange.domain.models.ExchangeRatesModels
import com.rate.manager.exchange.domain.repository.ExchangeRatesInterfase
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

class ExchangeRatesRepository(val arrayList: ArrayList<String>) : ExchangeRatesInterfase {

    override suspend fun exchangee(spin: String): ArrayList<ExchangeRatesModels> {
        val arrayListRes = ArrayList<ExchangeRatesModels>()
        try {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url("https://currency-converter-pro1.p.rapidapi.com/latest-rates?base=$spin")
                .get()
                .addHeader("X-RapidAPI-Key", "85910b09eamshdfee78236f66566p13229ajsn51e9ae1d810f")
                .addHeader("X-RapidAPI-Host", "currency-converter-pro1.p.rapidapi.com")
                .build()

            val response = client.newCall(request).execute()
            val jsonObject = response.body?.string()?.let { JSONObject(it) }
            val jss = jsonObject?.getJSONObject("result")
            for (i in 0 until arrayList.size) {
                try {
                    arrayListRes.add(
                        ExchangeRatesModels(
                            arrayList[i],
                            jss?.getString(arrayList[i]).toString()
                        )
                    )
                } catch (e: Exception) {
                    arrayListRes.add(ExchangeRatesModels(arrayList[i],""))
                }

            }
        } catch (e: Exception) {
            e.message
        }
        return arrayListRes
    }

    override suspend fun exchangee(
        number: String,
        month: String,
        year: String
    ): ArrayList<ExchangeRatesModels> {
        val arrayListRes = ArrayList<ExchangeRatesModels>()
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://currencyscoop.p.rapidapi.com/historical?date=$year-$month-$number")
            .get()
            .addHeader("X-RapidAPI-Key", "85910b09eamshdfee78236f66566p13229ajsn51e9ae1d810f")
            .addHeader("X-RapidAPI-Host", "currencyscoop.p.rapidapi.com")
            .build()

        val response = client.newCall(request).execute()
        val jsonObject = response.body?.string()?.let { JSONObject(it) }
        val jss = jsonObject?.getJSONObject("response")
        val jsss = jss?.getJSONObject("rates")
        for (i in 0 until arrayList.size) {
            try {
                arrayListRes.add(
                    ExchangeRatesModels(
                        arrayList[i],
                        jsss?.getString(arrayList[i]).toString()
                    )
                )
            } catch (e: java.lang.Exception) {
               arrayListRes.add(ExchangeRatesModels(arrayList[i],""))
            }
        }
        return arrayListRes
    }
}