package com.example.food_app.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.food_app.R
import com.example.food_app.data.remote.dto.popularMeal.MealByCategory
import com.example.food_app.data.remote.dto.randomMeal.Meal
import com.example.food_app.presentation.categories.CategoriesScreen
import com.example.food_app.presentation.mealByCategory.MealByCategoryScreen
import com.example.food_app.presentation.details.DetailsScreen
import com.example.food_app.presentation.favourite.FavouriteScreen
import com.example.food_app.presentation.home.HomeScreen
import com.example.food_app.presentation.home.HomeViewModel
import com.example.food_app.presentation.navigation.component.BottomAppBar
import com.example.food_app.presentation.navigation.component.BottomBarItem

@Composable
fun AppNavigation() {

    val bottomBarItems = remember {
        listOf(
            BottomBarItem(icon = R.drawable.home, text = "Home"),
            BottomBarItem(icon = R.drawable.favorite, text = "Favourite"),
            BottomBarItem(icon = R.drawable.category, text = "Categories"),
        )
    }

    val navController = rememberNavController()
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }

    selectedItem = remember(key1 = backStackState) {
        when (backStackState?.destination?.route) {
            Screen.Home.route -> 0
            Screen.Favourite.route -> 1
            Screen.Categories.route -> 2
            else -> 0
        }
    }

    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Screen.Home.route ||
                backStackState?.destination?.route == Screen.Favourite.route ||
                backStackState?.destination?.route == Screen.Categories.route
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (isBottomBarVisible) {
                BottomAppBar(
                    items = bottomBarItems,
                    selectedItem = selectedItem,
                    onItemClick = { index ->
                        when (index) {
                            0 -> navController.navigate(Screen.Home.route)

                            1 -> navController.navigate(Screen.Favourite.route)

                            2 -> navController.navigate(Screen.Categories.route)
                        }
                    }
                )
            }

        }){
        val bottomPadding = it.calculateBottomPadding()

        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {

            composable(route = Screen.Home.route) {
                val homeViewModel : HomeViewModel = hiltViewModel()
                val state = homeViewModel.homeState.collectAsState().value

                val navigateToDetails = homeViewModel.navigateToDetails.collectAsState().value
                val mealDetailsState = homeViewModel.mealDetailsState.collectAsState().value

                val navigateToCategories = homeViewModel.navigateToCategories.collectAsState().value
                val categoriesList = homeViewModel.categoriesList.collectAsState().value

                if (navigateToDetails && mealDetailsState != null) {
                    navController.currentBackStackEntry?.savedStateHandle?.set("meal", mealDetailsState)
                    navController.navigate(route = Screen.Details.route)
                    homeViewModel.resetNavigationState()
                }

                if(navigateToCategories && categoriesList.isNotEmpty()) {
                    navController.currentBackStackEntry?.savedStateHandle?.set("mealList", categoriesList)
                    navController.navigate(route = Screen.MealsByCategories.route)
                    homeViewModel.resetCategoryNavigationState()
                }

                HomeScreen(
                    state ,
                    onMealClick = {
                        navController.currentBackStackEntry?.savedStateHandle?.set("meal" , state.randomMeal)
                        navController.navigate(route = Screen.Details.route)
                    } ,
                    onPopularItemClick = { categoryMeal ->
                        homeViewModel.fetchMealDetails(categoryMeal.idMeal)
                    } ,
                    onCategoryClick = { category ->
                        homeViewModel.getMealsByCategory(category.strCategory)
                    }
                )
            }

            composable(route = Screen.Details.route) {
                navController.previousBackStackEntry?.savedStateHandle?.get<Meal?>("meal")
                    ?.let { meal ->
                        DetailsScreen(
                            meal = meal ,
                            onFavouriteClick = {}
                        )
                    }
            }

            composable(route = Screen.Favourite.route ){
                FavouriteScreen()
            }

            composable(route = Screen.Categories.route){
                CategoriesScreen()
            }

            composable(route = Screen.MealsByCategories.route){
                navController.previousBackStackEntry?.savedStateHandle?.get<List<MealByCategory>>("mealList")
                    ?.let { mealList ->
                        MealByCategoryScreen(mealList = mealList)
                    }
            }
        }
    }


}
