package com.example.food_app.domain

import com.example.food_app.data.remote.dto.popularMeal.CategoryMeal
import com.example.food_app.data.remote.dto.randomMeal.Meal
import com.example.food_app.data.remote.dto.randomMeal.MealList

interface MealRepository {
    suspend fun getRandomMeal() : MealList

    suspend fun getPopularItems(
        categoryName : String
    ): List<CategoryMeal>

    suspend fun getMealDetails(
        id: String
    ): Meal
}