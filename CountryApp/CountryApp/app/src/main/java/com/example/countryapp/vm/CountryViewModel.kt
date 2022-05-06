package com.example.countryapp.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countryapp.api.ApiRepo
import com.example.countryapp.util.StateResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CountryViewModel (
    private  val repository: ApiRepo,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
): ViewModel() {

    private val _countryData = MutableLiveData<StateResult>()
    val countryData : LiveData<StateResult>
        get() = _countryData

    init {
        getCountries()
    }

    private fun getCountries() {
        viewModelScope.launch(coroutineDispatcher) {
            repository.fetchCounties().collect {
                _countryData.postValue(it)
            }
        }
    }
}