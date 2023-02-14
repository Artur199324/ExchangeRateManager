package com.rate.manager.exchange.domain.repository

import com.rate.manager.exchange.domain.models.ConvertModels

interface ConvertRepositoryInterface {

    suspend fun convert(from:String,to:String,amount:String): ConvertModels
}