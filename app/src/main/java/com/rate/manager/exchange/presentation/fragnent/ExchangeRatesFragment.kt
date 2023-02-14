package com.rate.manager.exchange.presentation.fragnent

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.rate.manager.exchange.R
import com.rate.manager.exchange.data.repository.ExchangeRatesRepository
import com.rate.manager.exchange.databinding.FragmentExchangeRatesBinding
import com.rate.manager.exchange.domain.repository.ExchangeRatesInterfase
import com.rate.manager.exchange.domain.userCase.ExchangeRatesUserCase
import com.rate.manager.exchange.presentation.adapter.AdaptarExchange
import com.rate.manager.exchange.presentation.viewModel.ConvertViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class ExchangeRatesFragment : Fragment() {

    lateinit var binding: FragmentExchangeRatesBinding
    lateinit var Lines: List<String>
    var spin = 0
    val viewModel: ConvertViewModel by activityViewModels()
    lateinit var exchangeRatesInterfase: ExchangeRatesInterfase
    lateinit  var exchangeRatesUserCase:ExchangeRatesUserCase
    private var updatesJob: Job? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExchangeRatesBinding.inflate(inflater, container, false)
        binding.rexxx.layoutManager = LinearLayoutManager(activity?.applicationContext)
        Lines = resources.getStringArray(R.array.currencies).toList()
        exchangeRatesInterfase = ExchangeRatesRepository(Lines as ArrayList<String>)
        exchangeRatesUserCase = ExchangeRatesUserCase(exchangeRatesInterfase)
        val arrayAdapter: ArrayAdapter<*>
        arrayAdapter =
            ArrayAdapter(binding.root.context, android.R.layout.simple_list_item_1, Lines)
        binding.spinner4.adapter = arrayAdapter
        binding.spinner4.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                spin = p2

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {


            }

        }
        binding.go.setOnClickListener {
            updatesJob =  lifecycleScope.launch(Dispatchers.IO) {
               val ex =viewModel.exhange(Lines[spin], exchangeRatesUserCase)

                lifecycleScope.launch(Dispatchers.Main){
                    val adapter = activity?.applicationContext?.let { it1 -> AdaptarExchange(it1,ex) }
                    binding.rexxx.adapter = adapter
                }

            }
        }

        return binding.root
    }

}