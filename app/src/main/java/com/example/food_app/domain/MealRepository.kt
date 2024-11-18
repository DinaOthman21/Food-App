package com.example.food_app.domain

import com.example.food_app.data.remote.dto.randomMeal.MealList

interface MealRepository {
    suspend fun getRandomMeal() : MealList
}