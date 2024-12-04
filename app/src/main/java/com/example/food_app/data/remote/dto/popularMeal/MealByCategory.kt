package com.example.food_app.data.remote.dto.popularMeal

import java.io.Serializable

data class MealByCategory(
    val idMeal: String,
    val strMeal: String,
    val strMealThumb: String
) : Serializable