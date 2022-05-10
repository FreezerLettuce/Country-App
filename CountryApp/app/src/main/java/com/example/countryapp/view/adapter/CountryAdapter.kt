package com.example.countryapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.countryapp.databinding.CountryItemBinding
import com.example.countryapp.model.Country

class CountryAdapter(
    private val dataSet: MutableList<Country> = mutableListOf()
) : RecyclerView.Adapter<CountryViewHolder>() {

    fun updateDataSet(newDataSet: List<Country>) {
        dataSet.addAll(newDataSet)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder =
        CountryViewHolder(
            CountryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) =
        holder.bind(dataSet[position])

    override fun getItemCount(): Int = dataSet.size
}

class CountryViewHolder(
    private val binding: CountryItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(country: Country) {
        binding.countryName.text = country.name + ", "
        binding.countryCapital.text = country.capital
        binding.countryCode.text = country.code
        binding.countryRegion.text = country.region
    }
}