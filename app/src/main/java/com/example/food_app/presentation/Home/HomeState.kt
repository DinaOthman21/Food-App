package com.example.food_app.presentation.Home

import com.example.food_app.data.remote.dto.Meal

data class HomeState(
    val isLoading: Boolean = false,
    val meal: Meal? = null,
    val error: String? = null
)
