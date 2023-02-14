package com.rate.manager.exchange.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.rate.manager.exchange.domain.userCase.AdjaUserCase
import com.rate.manager.exchange.domain.userCase.SaveUserCase
import com.rate.manager.exchange.presentation.activity.intefase.AdjastResalt

class SplashViewModel:ViewModel() {

    fun getSave(saveUserCase: SaveUserCase):String{
      return saveUserCase.execute()
    }

    fun setSave(saveUserCase: SaveUserCase,s:String){
        saveUserCase.execute(s)
    }

    fun adj(adjaUserCase: AdjaUserCase,adjastResalt: AdjastResalt){
        adjaUserCase.execute(adjastResalt)
    }
}