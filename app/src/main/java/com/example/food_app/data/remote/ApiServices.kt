package com.example.food_app.data.remote

import com.example.food_app.data.remote.dto.MealList
import retrofit2.http.GET


interface ApiServices {

    @GET("random.php")
   suspend fun getRandomMeal() : MealList
}