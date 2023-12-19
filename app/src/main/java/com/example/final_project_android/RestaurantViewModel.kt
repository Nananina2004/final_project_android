package com.example.final_project_android

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RestaurantViewModel(private val twoGisApiClient: PlacesApiClient = PlacesApiClient()) : ViewModel() {

    private val _restaurants = MutableLiveData<List<PlacesApiService.Place>>(emptyList())
    val restaurants: LiveData<List<PlacesApiService.Place>> get() = _restaurants

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _favouriteRestaurants = MutableLiveData<List<PlacesApiService.Place>>(emptyList())
    val favouriteRestaurants: LiveData<List<PlacesApiService.Place>> get() = _favouriteRestaurants

    fun loadRestaurants(category: String) {
        twoGisApiClient.getRestaurants(category, object: PlacesApiClient.PlacesCallback {
            override fun onPlacesFetched(restaurants: List<PlacesApiService.Place>) {
                _restaurants.value = restaurants
                _favouriteRestaurants.value = restaurants.filter { it.isFavourite }
            }

            override fun onError(errorMessage: String) {
                _error.value = errorMessage
            }
        })
    }

    fun toggleFavourite(place: PlacesApiService.Place) {
        val updatedList = _restaurants.value?.map {
            if (it == place) {
                it.copy(isFavourite = !it.isFavourite)
            } else {
                it
            }
        }
        _restaurants.value = updatedList ?: emptyList()
        _favouriteRestaurants.value = updatedList?.filter { it.isFavourite } ?: emptyList()
    }
}
