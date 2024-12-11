package com.example.food_app.data.repository

import com.example.food_app.data.local.MealDatabase
import com.example.food_app.data.remote.ApiServices
import com.example.food_app.data.remote.dto.categories.CategoryList
import com.example.food_app.data.remote.dto.popularMeal.MealByCategory
import com.example.food_app.data.remote.dto.randomMeal.Meal
import com.example.food_app.data.remote.dto.randomMeal.MealList
import com.example.food_app.domain.MealRepository
import kotlinx.coroutines.flow.Flow


class MealRepositoryImpl (
    private val api : ApiServices ,
    private val mealDatabase: MealDatabase
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

    override suspend fun getMealsByCategory(categoryName: String): List<MealByCategory> {
        return api.getMealsByCategory(
            categoryName = categoryName
        ).meals
    }

    override suspend fun upsertMeal(meal: Meal) {
        mealDatabase.mealDao.upsertMeal(meal = meal)
    }

    override suspend fun deleteMeal(meal: Meal) {
        mealDatabase.mealDao.deleteMeal(meal = meal)
    }

    override suspend fun getAllMeals(): Flow<List<Meal>> {
      return mealDatabase.mealDao.getAllMeals()
    }

    override suspend fun getMealFromDB(mealId: String): Meal {
        return mealDatabase.mealDao.getMealById(mealId = mealId)
    }

}