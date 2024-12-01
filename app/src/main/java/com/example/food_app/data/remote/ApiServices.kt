package com.example.food_app.data.remote

import com.example.food_app.data.remote.dto.categories.CategoryList
import com.example.food_app.data.remote.dto.popularMeal.MealsByCategoryList
import com.example.food_app.data.remote.dto.randomMeal.MealList
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiServices {

    @GET("random.php")
   suspend fun getRandomMeal() : MealList

    @GET("filter.php?")
    suspend fun getPopularItems(
        @Query("c") categoryName : String
    ) :MealsByCategoryList

    @GET("lookup.php?")
    suspend fun getMealDetails(
        @Query("i") id : String
    ) : MealList

    @GET("categories.php")
    suspend fun getCategories() : CategoryList
}