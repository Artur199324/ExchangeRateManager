package com.rate.manager.exchange.domain.userCase

import android.util.Log
import com.rate.manager.exchange.domain.models.ConvertModels
import com.rate.manager.exchange.domain.repository.ConvertRepositoryInterface

class ConvertUserCase(private val convertRepositoryInterface: ConvertRepositoryInterface) {

    suspend fun execute(from:String,to:String,amount:String): ConvertModels {

      return  convertRepositoryInterface.convert(from, to, amount)
    }

}