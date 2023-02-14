package com.rate.manager.exchange.domain.repository

import com.rate.manager.exchange.domain.models.ExchangeRatesModels

interface ExchangeRatesInterfase {

    suspend fun exchangee(spin:String):ArrayList<ExchangeRatesModels>
    suspend fun exchangee(number:String,month:String,year:String):ArrayList<ExchangeRatesModels>
}