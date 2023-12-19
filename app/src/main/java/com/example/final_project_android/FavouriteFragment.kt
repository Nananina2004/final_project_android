package com.example.final_project_android

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.final_project_android.databinding.FragmentFavouriteBinding

class FavouriteFragment : Fragment(R.layout.fragment_favourite) {

    private lateinit var binding: FragmentFavouriteBinding
    private val viewModel by viewModels<RestaurantViewModel>()
    private lateinit var favouritesAdapter: FavouritesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initObserver()
    }

    private fun initRecyclerView() = with(binding) {
        favouritesAdapter = FavouritesAdapter(emptyList())
        favouriteRecycler.adapter = favouritesAdapter
        favouriteRecycler.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
    }

    private fun initObserver() {
        viewModel.favouriteRestaurants.observe(viewLifecycleOwner) { favouriteRestaurants ->
            favouritesAdapter.updateData(favouriteRestaurants)
        }
    }
}