package com.example.food_app.data.repository

import com.example.food_app.data.remote.ApiServices
import com.example.food_app.data.remote.dto.MealList
import com.example.food_app.domain.MealRepository

class MealRepositoryImpl (
    private val api : ApiServices
) : MealRepository {

    override suspend fun getRandomMeal(): MealList {
        return api.getRandomMeal()
    }

}