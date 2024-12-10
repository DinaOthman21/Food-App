package com.example.food_app.presentation.favourite

import com.example.food_app.data.remote.dto.randomMeal.Meal

data class FavouritesScreenState(
    val favouriteMeals : List<Meal> = emptyList()
)
