package com.example.countryapp.view

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countryapp.api.ApiRepoImp
import com.example.countryapp.api.Rest
import com.example.countryapp.databinding.ActivityMainBinding
import com.example.countryapp.util.StateResult
import com.example.countryapp.view.adapter.CountryAdapter
import com.example.countryapp.vm.CountryViewModel

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val mAdapter by lazy {
        CountryAdapter()
    }

    private val cViewModel by lazy {
        ViewModelProvider(
            this,
            object: ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return CountryViewModel(
                        ApiRepoImp(Rest.apiService)
                    ) as T
                }

            })[CountryViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.countryRecycler.apply {
            layoutManager = LinearLayoutManager(baseContext, LinearLayoutManager.VERTICAL, false)
            adapter = mAdapter
        }
    }

    override fun onResume() {
        super.onResume()

        cViewModel.countryData.observe(this) { mState ->
            when(mState) {
                is StateResult.LOADING -> {
                    Toast.makeText(baseContext, "LOADING COUNTRIES...", Toast.LENGTH_LONG).show()
                }
                is StateResult.SUCCESS -> {
                    mAdapter.updateDataSet(mState.result)
                }
                is StateResult.ERROR -> {
                    AlertDialog.Builder(binding.root.context)
                        .setTitle("You got an error!")
                        .setMessage(mState.error.localizedMessage)
                        .setNegativeButton("DISMISS") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .create()
                        .show()
                }
            }

        }
    }
}