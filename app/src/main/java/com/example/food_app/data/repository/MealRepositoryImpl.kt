package com.example.food_app.data.repository

import com.example.food_app.data.remote.ApiServices
import com.example.food_app.data.remote.dto.categories.CategoryList
import com.example.food_app.data.remote.dto.popularMeal.MealByCategory
import com.example.food_app.data.remote.dto.randomMeal.Meal
import com.example.food_app.data.remote.dto.randomMeal.MealList
import com.example.food_app.domain.MealRepository


class MealRepositoryImpl (
    private val api : ApiServices
) : MealRepository {

    override suspend fun getRandomMeal(): MealList {
        return api.getRandomMeal()
    }

    override suspend fun getPopularItems(categoryName: String): List<MealByCategory> {
        return api.getPopularItems(
            categoryName = categoryName
        ).meals
    }

    override suspend fun getMealDetails(id: String): Meal {
        return api.getMealDetails(id).meals.first()
    }

    override suspend fun getCategories(): CategoryList {
        return api.getCategories()
    }

}