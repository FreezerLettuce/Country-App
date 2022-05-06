package com.example.countryapp.util

import com.example.countryapp.model.Country

sealed class StateResult {
    object LOADING : StateResult()
    data class SUCCESS(val result: List<Country>): StateResult()
    data class ERROR(val error: Exception): StateResult()
}
