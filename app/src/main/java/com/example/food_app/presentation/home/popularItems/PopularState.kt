package com.example.food_app.presentation.home.popularItems

import com.example.food_app.data.remote.dto.popularMeal.CategoryMeal

data class PopularState(
    val isLoading : Boolean = false ,
    val popularItems : List<CategoryMeal> =  emptyList() ,
    val error: String? = null
)
