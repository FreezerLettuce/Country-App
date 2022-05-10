package com.example.countryapp.api

import com.example.countryapp.util.StateResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface ApiRepo {
    fun fetchCounties():  Flow<StateResult>
}

class ApiRepoImp(private val service: ApiService): ApiRepo{

    override fun fetchCounties(): Flow<StateResult> = flow {
        emit(StateResult.LOADING)
        delay(700)

        try{
            val data = service.fetchCountries()
            if(data.isSuccessful) {
                data.body()?.let {
                    emit(StateResult.SUCCESS(it))
                } ?: throw Exception("Error response as null")
            } else {
                val error = data.errorBody()?.string() ?: "Failure response"
                throw Exception(error)
            }
        }catch (e: Exception){
            emit(StateResult.ERROR(e))
        }
    }
}