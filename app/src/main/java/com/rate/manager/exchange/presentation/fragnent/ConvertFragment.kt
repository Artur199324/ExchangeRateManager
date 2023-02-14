package com.rate.manager.exchange.presentation.fragnent

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.rate.manager.exchange.data.repository.ConvertRepository
import com.rate.manager.exchange.databinding.FragmentConvertBinding
import com.rate.manager.exchange.domain.repository.ConvertRepositoryInterface
import com.rate.manager.exchange.domain.userCase.ConvertUserCase
import com.rate.manager.exchange.presentation.viewModel.ConvertViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*


class ConvertFragment : Fragment() {

    lateinit var binding: FragmentConvertBinding
    lateinit var Lines: List<String>
    var spin1 = 0
    var spin2 = 1
    private var updatesJob: Job? = null
    val viewModel: ConvertViewModel by activityViewModels()
    val convertRepository: ConvertRepositoryInterface = ConvertRepository()
    val convertUserCase = ConvertUserCase(convertRepository)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentConvertBinding.inflate(inflater, container, false)
        Lines = resources.getStringArray(com.rate.manager.exchange.R.array.currencies).toList()
        val arrayAdapter: ArrayAdapter<*>
        arrayAdapter = ArrayAdapter(binding.root.context, R.layout.simple_list_item_1, Lines)
        binding.spinner2.adapter = arrayAdapter
        binding.spinner3.adapter = arrayAdapter
        binding.spinner3.setSelection(1)


        binding.spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                itemSelected: View, selectedItemPosition: Int, selectedId: Long
            ) {
                spin1 = selectedItemPosition
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.spinner3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                spin2 = p2
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }

        binding.convert.setOnClickListener {
            binding.result.text = ""
            binding.allResFrom.text = ""
            binding.allResTo.text = ""
            val amount = binding.sum.text.toString()
            if (amount == "") {
                Toast.makeText(context, "Введите сумму", Toast.LENGTH_LONG).show()
            } else {
                updatesJob = lifecycleScope.launch(Dispatchers.IO) {

                    val convertModels =
                        viewModel.convert(convertUserCase, Lines[spin1], Lines[spin2], amount)
                    if (convertModels.result.isNotEmpty()) {
                        Log.d("weq", convertModels.result)
                        Log.d("weq", convertModels.allResFrom)
                        Log.d("weq", convertModels.allResTo)
                        lifecycleScope.launch(Dispatchers.Main){
                            binding.result.text = convertModels.result
                            binding.allResFrom.text = convertModels.allResFrom
                            binding.allResTo.text = convertModels.allResTo
                        }

                    } else {
                        Toast.makeText(context, "Нет интернета", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
        return binding.root
    }


}