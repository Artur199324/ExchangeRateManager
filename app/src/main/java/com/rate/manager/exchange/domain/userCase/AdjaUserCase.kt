package com.rate.manager.exchange.domain.userCase

import com.rate.manager.exchange.domain.repository.AdjaRepositoryIntefase
import com.rate.manager.exchange.presentation.activity.intefase.AdjastResalt

class AdjaUserCase(val adjaRepositoryIntefase: AdjaRepositoryIntefase) {
    fun execute(adjastResalt: AdjastResalt){
        adjaRepositoryIntefase.statAdjas(adjastResalt)
    }
}