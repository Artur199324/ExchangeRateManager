package com.rate.manager.exchange.presentation.fragnent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.rate.manager.exchange.R
import com.rate.manager.exchange.data.repository.ExchangeRatesRepository
import com.rate.manager.exchange.databinding.FragmentHistoricalCourseBinding
import com.rate.manager.exchange.domain.repository.ExchangeRatesInterfase
import com.rate.manager.exchange.domain.userCase.ExchangeRatesUserCase
import com.rate.manager.exchange.presentation.adapter.AdaptarExchange
import com.rate.manager.exchange.presentation.viewModel.ConvertViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class HistoricalCourseFragment : Fragment() {

    lateinit var binding: FragmentHistoricalCourseBinding
    val viewModel: ConvertViewModel by activityViewModels()
    lateinit var exchangeRatesInterfase: ExchangeRatesInterfase
    lateinit  var exchangeRatesUserCase: ExchangeRatesUserCase
    private var updatesJob: Job? = null
    lateinit var Lines: List<String>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoricalCourseBinding.inflate(inflater, container, false)
        Lines = resources.getStringArray(R.array.currencies).toList()
        exchangeRatesInterfase = ExchangeRatesRepository(Lines as ArrayList<String>)
        exchangeRatesUserCase = ExchangeRatesUserCase(exchangeRatesInterfase)
        binding.recc.layoutManager = LinearLayoutManager(activity?.applicationContext)
        binding.gooo.setOnClickListener {
            if (binding.editTextDate2.text.isNotEmpty() && binding.editTextDate3.text.isNotEmpty() && binding.editTextDate4.text.isNotEmpty()){
                updatesJob = lifecycleScope.launch(Dispatchers.IO){
                 val  ex =  viewModel.exhange(binding.editTextDate2.text.toString(),binding.editTextDate3.text.toString(),binding.editTextDate4.text.toString(),exchangeRatesUserCase)
                    lifecycleScope.launch(Dispatchers.Main){
                        val adapter = activity?.applicationContext?.let { it1 -> AdaptarExchange(it1,ex) }
                        binding.recc.adapter = adapter
                    }
                }

            }else{
                Toast.makeText(activity?.applicationContext,"Введите дату",Toast.LENGTH_LONG).show()
            }
        }


        return binding.root
    }

}