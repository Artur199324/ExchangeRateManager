package com.rate.manager.exchange.data.repository

import android.content.Context
import android.util.Log
import com.adjust.sdk.Adjust
import com.adjust.sdk.AdjustConfig
import com.rate.manager.exchange.data.Da
import com.rate.manager.exchange.domain.models.AdjastModel
import com.rate.manager.exchange.domain.repository.AdjaRepositoryIntefase
import com.rate.manager.exchange.presentation.activity.SplashActivity
import com.rate.manager.exchange.presentation.activity.intefase.AdjastResalt

class AdjaRepository(val context: SplashActivity):AdjaRepositoryIntefase {
    var s1 = ""
    var s2 = ""
    var s3 = ""
    var s4 = ""
    override fun statAdjas(adjastResalt: AdjastResalt) {
        Log.d("weq","wwww")
        val en = AdjustConfig.ENVIRONMENT_PRODUCTION
        val con = AdjustConfig(context.applicationContext,Da.kka,en)
        con.setOnAttributionChangedListener{
            Log.d("weq","22222")
            if (it.trackerName != Da.kkOr){
                Log.d("weq","3333")
                s1 = if (it.campaign.isNotEmpty()){
                    it.campaign
                }else{
                    Da.nnn
                }
                s2 = if (it.adgroup.isNotEmpty()){
                    it.adgroup
                }else{
                    Da.nnn
                }

                s3 = if (it.creative.isNotEmpty()){
                    it.creative
                }else{
                    Da.nnn
                }

                s4 = if (it.adid.isNotEmpty()){
                    it.adid
                }else{
                    Da.nnn
                }

                adjastResalt.resalt(AdjastModel(s1,s2,s3,s4))
            }else{
                adjastResalt.resalt(AdjastModel("n","n","n","n"))
            }
        }

        Adjust.onCreate(con)
        Adjust.onResume()

    }
}