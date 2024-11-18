package com.example.food_app.data.remote

import com.example.food_app.data.remote.dto.popularMeal.CategoryList
import com.example.food_app.data.remote.dto.randomMeal.MealList
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiServices {

    @GET("random.php")
   suspend fun getRandomMeal() : MealList

    @GET("filter.php?")
    fun getPopularItems(
        @Query("c") categoryName : String
    ) :CategoryList
}