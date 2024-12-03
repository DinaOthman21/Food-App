package com.example.food_app.domain

import com.example.food_app.data.remote.dto.categories.CategoryList
import com.example.food_app.data.remote.dto.popularMeal.MealByCategory
import com.example.food_app.data.remote.dto.randomMeal.Meal
import com.example.food_app.data.remote.dto.randomMeal.MealList

interface MealRepository {
    suspend fun getRandomMeal() : MealList

    suspend fun getPopularItems(
        categoryName : String
    ): List<MealByCategory>

    suspend fun getMealDetails(
        id: String
    ): Meal

    suspend fun getCategories() : CategoryList

    suspend fun getMealsByCategory(
        categoryName : String
    ) : List<MealByCategory>
}