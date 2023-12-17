package com.jaaveeth.weatherapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jaaveeth.weatherapp.R
import com.jaaveeth.weatherapp.databinding.ListItemBinding
import com.jaaveeth.weatherapp.domain.entities.WeatherInfo
import com.jaaveeth.weatherapp.utils.Conversion.kelvinToCelsius

class WeatherListAdapter(var postList: MutableList<WeatherInfo>) : RecyclerView.Adapter<WeatherListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding : ListItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.list_item,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(postList)
    }

    override fun getItemCount(): Int  = postList.size

    class ViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(postList: MutableList<WeatherInfo>) {
            binding.tvDay.text = postList[adapterPosition].dayOfWeek
            binding.tvTemperature.text = kelvinToCelsius(postList[adapterPosition].temperature).plus("C")


            if (adapterPosition == (postList.size-1)) binding.viewline.visibility = View.GONE
            else binding.viewline.visibility = View.VISIBLE
        }
    }

}