package com.example.food_app.presentation.home

import com.example.food_app.data.remote.dto.randomMeal.Meal

data class HomeState(
    val isLoading: Boolean = false,
    val meal: Meal? = null,
    val error: String? = null
)