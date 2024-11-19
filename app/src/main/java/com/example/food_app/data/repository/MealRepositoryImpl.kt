package com.example.food_app.data.repository

import com.example.food_app.data.remote.ApiServices
import com.example.food_app.data.remote.dto.popularMeal.CategoryMeal
import com.example.food_app.data.remote.dto.randomMeal.MealList
import com.example.food_app.domain.MealRepository


class MealRepositoryImpl (
    private val api : ApiServices
) : MealRepository {

    override suspend fun getRandomMeal(): MealList {
        return api.getRandomMeal()
    }

    override suspend fun getPopularItems(categoryName: String): List<CategoryMeal> {
        return api.getPopularItems(
            categoryName = categoryName
        ).meals
    }

}