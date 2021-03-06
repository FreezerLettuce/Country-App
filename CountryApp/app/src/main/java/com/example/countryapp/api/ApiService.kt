package com.example.countryapp.api

import com.example.countryapp.model.Country
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(PATH_COUNTRIES)
    suspend fun fetchCountries(): Response<List<Country>>

    companion object {
        private const val PATH_COUNTRIES = "countries.json"
        const val BASE_URL = "https://gist.githubusercontent.com/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/"
        //note: The original URL given was not working to fetch the countries JSON was not working was giving 404 error. Hopefully this new link resolves the issue
    }
}