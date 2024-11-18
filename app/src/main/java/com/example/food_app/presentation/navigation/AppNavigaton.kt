package com.example.food_app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.food_app.data.remote.dto.Meal
import com.example.food_app.presentation.details.DetailsScreen
import com.example.food_app.presentation.home.HomeScreen
import com.example.food_app.presentation.home.HomeViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {

        composable(route = Screen.Home.route) {
            val homeViewModel : HomeViewModel = hiltViewModel()
            val state = homeViewModel.mealState.collectAsState().value
            HomeScreen(
                state ,
                onMealClick = {
                    navController.currentBackStackEntry?.savedStateHandle?.set("meal" , state.meal)
                    navController.navigate(route = Screen.Details.route)
                }
            )
        }

        composable(route = Screen.Details.route) {
            navController.previousBackStackEntry?.savedStateHandle?.get<Meal?>("meal")
                ?.let { meal ->
                    DetailsScreen(
                        meal = meal ,
                        onFavouriteClick = {} ,
                        onWatchClick = {}
                    )
                }
        }

    }
}