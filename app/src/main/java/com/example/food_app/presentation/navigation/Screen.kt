package com.example.food_app.presentation.navigation

sealed class Screen(val route :String){
    data object Home : Screen("HomeScreen")
    data object Favourite : Screen("FavouriteScreen")
    data object Categories : Screen("CategoriesScreen")
    data object Details : Screen("DetailsScreen")
}