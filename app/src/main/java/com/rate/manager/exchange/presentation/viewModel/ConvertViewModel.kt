package com.rate.manager.exchange.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.rate.manager.exchange.domain.models.ConvertModels
import com.rate.manager.exchange.domain.models.ExchangeRatesModels
import com.rate.manager.exchange.domain.userCase.ConvertUserCase
import com.rate.manager.exchange.domain.userCase.ExchangeRatesUserCase

class ConvertViewModel: ViewModel() {

    suspend fun convert(covertUserCase: ConvertUserCase,from:String,to:String,amount:String): ConvertModels {
       return covertUserCase.execute(from, to, amount)
    }

    suspend fun exhange(spin: String, exchangeRatesUserCase: ExchangeRatesUserCase):ArrayList<ExchangeRatesModels>{
       return exchangeRatesUserCase.execute(spin)
    }

    suspend fun exhange(number:String,month:String,year:String, exchangeRatesUserCase: ExchangeRatesUserCase):ArrayList<ExchangeRatesModels>{
        return exchangeRatesUserCase.execute(number,month,year)
    }
}