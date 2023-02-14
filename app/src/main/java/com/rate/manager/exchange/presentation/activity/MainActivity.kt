package com.rate.manager.exchange.presentation.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.rate.manager.exchange.R

class MainActivity : AppCompatActivity() {

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        val bu1 = findViewById<TextView>(R.id.bu1)
        val bu2 = findViewById<TextView>(R.id.bu2)
        val bu3 = findViewById<TextView>(R.id.bu3)

        bu1.setOnClickListener {
            navController.navigate(R.id.blankFragment)
            bu2.background = null
            bu1.background = resources.getDrawable(R.drawable.bac_butt)
            bu3.background = null
        }
        bu2.setOnClickListener {
            navController.navigate(R.id.exchangeRatesFragment)
            bu2.background = resources.getDrawable(R.drawable.bac_butt)
            bu1.background = null
            bu3.background = null
        }
        bu3.setOnClickListener {
            navController.navigate(R.id.historicalCourseFragment)
            bu2.background = null
            bu1.background = null
            bu3.background = resources.getDrawable(R.drawable.bac_butt)
        }

    }
}