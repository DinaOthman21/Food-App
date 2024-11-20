package com.example.food_app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.food_app.data.remote.dto.randomMeal.Meal
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
            val navigateToDetails = homeViewModel.navigateToDetails.collectAsState().value
            val mealDetailsState = homeViewModel.mealDetailsState.collectAsState().value

            if (navigateToDetails && mealDetailsState != null) {
                navController.currentBackStackEntry?.savedStateHandle?.set("meal", mealDetailsState)
                navController.navigate(route = Screen.Details.route)
                homeViewModel.resetNavigationState()
            }

            HomeScreen(
                state ,
                onMealClick = {
                    navController.currentBackStackEntry?.savedStateHandle?.set("meal" , state.randomMeal)
                    navController.navigate(route = Screen.Details.route)
                } ,
                onPopularItemClick = { categoryMeal ->
                    homeViewModel.fetchMealDetails(categoryMeal.idMeal)
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