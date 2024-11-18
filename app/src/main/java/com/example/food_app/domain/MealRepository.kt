package com.example.food_app.domain

import com.example.food_app.data.remote.dto.popularMeal.CategoryMeal
import com.example.food_app.data.remote.dto.randomMeal.MealList
import com.example.food_app.util.Resource
import kotlinx.coroutines.flow.Flow

interface MealRepository {
    suspend fun getRandomMeal() : MealList
    suspend fun getPopularItems(
        categoryName : String
    ) : Flow<Resource<List<CategoryMeal>>>
}