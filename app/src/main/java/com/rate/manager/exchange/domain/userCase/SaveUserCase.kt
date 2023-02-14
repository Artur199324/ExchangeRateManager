package com.rate.manager.exchange.domain.userCase

import com.rate.manager.exchange.domain.repository.SaveInterface

class SaveUserCase(val saveInterface: SaveInterface) {

    fun execute():String{
        return saveInterface.getData()
    }

    fun execute(s:String){
        saveInterface.setData(s)
    }
}