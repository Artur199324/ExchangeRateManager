package com.rate.manager.exchange.domain.userCase

import com.rate.manager.exchange.domain.models.ExchangeRatesModels
import com.rate.manager.exchange.domain.repository.ExchangeRatesInterfase

class ExchangeRatesUserCase(val exchangeRatesInterfase: ExchangeRatesInterfase) {

   suspend fun execute(spin: String):ArrayList<ExchangeRatesModels>{
        return exchangeRatesInterfase.exchangee(spin)
    }

    suspend fun execute(number:String,month:String,year:String):ArrayList<ExchangeRatesModels>{
        return exchangeRatesInterfase.exchangee(number,month,year)
    }
}