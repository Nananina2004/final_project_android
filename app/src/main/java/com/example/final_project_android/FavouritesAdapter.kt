package com.example.final_project_android

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FavouritesAdapter(
    private var restaurants: List<PlacesApiService.Place>
) : RecyclerView.Adapter<FavouritesAdapter.RestaurantViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val favouriteRestaurant = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favourite_restaurant, parent, false)

        return RestaurantViewHolder(favouriteRestaurant)
    }

    override fun getItemCount(): Int = restaurants.size

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val favouriteRestaurant = restaurants[position]
        holder.bind(favouriteRestaurant)
    }

    fun updateData(data: List<PlacesApiService.Place>) {
        restaurants = data
        notifyDataSetChanged()
    }

    inner class RestaurantViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tvName)
        val address: TextView = itemView.findViewById(R.id.tvAddress)

        fun bind(place: PlacesApiService.Place) {
            name.text = place.name
            address.text = place.address
        }
    }
}
