package com.example.food_app.presentation.home

import com.example.food_app.data.remote.dto.categories.Category
import com.example.food_app.data.remote.dto.popularMeal.MealByCategory
import com.example.food_app.data.remote.dto.randomMeal.Meal

data class HomeState(

    //Random Meal
    val randomIsLoading: Boolean = false,
    val randomMeal: Meal? = null,
    val randomError: String? = null,

    //Popular Items
    val popularIsLoading : Boolean = false,
    val popularItems : List<MealByCategory> =  emptyList(),
    val popularError: String? = null ,

    //category List
    val categoryIsLoading : Boolean = false,
    val categoriesList :List<Category> = emptyList(),
    val categoryError: String? = null
)
