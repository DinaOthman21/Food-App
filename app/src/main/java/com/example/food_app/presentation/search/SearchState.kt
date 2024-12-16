package com.example.food_app.presentation.search

import com.example.food_app.data.remote.dto.randomMeal.Meal

data class SearchState(
    val isLoading : Boolean = false,
    val searchQuery : String = "",
    val meals: List<Meal>? = emptyList(),
    val searchError: String? = null
)
