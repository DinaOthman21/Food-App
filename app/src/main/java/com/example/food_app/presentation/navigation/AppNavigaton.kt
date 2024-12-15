package com.example.food_app.presentation.navigation

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.food_app.R
import com.example.food_app.data.remote.dto.randomMeal.Meal
import com.example.food_app.presentation.categories.CategoriesScreen
import com.example.food_app.presentation.mealByCategory.MealByCategoryScreen
import com.example.food_app.presentation.details.DetailsScreen
import com.example.food_app.presentation.details.DetailsViewModel
import com.example.food_app.presentation.favourite.FavouriteScreen
import com.example.food_app.presentation.favourite.FavouritesViewModel
import com.example.food_app.presentation.home.HomeScreen
import com.example.food_app.presentation.home.HomeViewModel
import com.example.food_app.presentation.mealByCategory.MealByCategoryViewModel
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

        }){ it ->
        val bottomPadding = it.calculateBottomPadding()

        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(bottom = bottomPadding)
        ) {

            composable(route = Screen.Home.route) {
                val homeViewModel : HomeViewModel = hiltViewModel()
                val state = homeViewModel.homeState.collectAsState().value

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
                        navController.currentBackStackEntry?.savedStateHandle?.set("selectedCategory", category.strCategory)
                        navController.navigate(Screen.MealsByCategories.route)
                    }
                )
                val meal = homeViewModel.meal.collectAsState().value
                LaunchedEffect(meal) {
                    meal?.let {
                        navController.currentBackStackEntry?.savedStateHandle?.set("meal", it)
                        navController.navigate(route = Screen.Details.route)
                        homeViewModel.resetMealState()
                    }
                }
            }

            composable(route = Screen.Details.route) {
                val detailsViewModel:DetailsViewModel = hiltViewModel()
                if (detailsViewModel.sideEffect != null){
                    Toast.makeText(
                        LocalContext.current ,detailsViewModel.sideEffect ,Toast.LENGTH_SHORT
                    ).show()
                    detailsViewModel.removeSideEffect()
                }
                navController.previousBackStackEntry?.savedStateHandle?.get<Meal?>("meal")
                    ?.let { meal ->
                        DetailsScreen(
                            meal = meal ,
                            onFavouriteClick = {
                                detailsViewModel.onFavouriteClick(meal)
                            }
                        )
                    }
            }

            composable(route = Screen.Favourite.route ){
                val favouritesViewModel : FavouritesViewModel = hiltViewModel()
                val state = favouritesViewModel.favouritesState.value
                FavouriteScreen(
                    state = state,
                    onMealClick = { meal ->
                        navController.currentBackStackEntry?.savedStateHandle?.set("meal",meal)
                        navController.navigate(route = Screen.Details.route)
                    }
                )
            }

            composable(route = Screen.Categories.route){
                val homeViewModel : HomeViewModel = hiltViewModel()
                homeViewModel.getCategoriesList()
                val state = homeViewModel.homeState.collectAsState().value
                CategoriesScreen(
                    categoriesList = state.categoriesList ,
                    onCategoryClick = { category ->
                        navController.currentBackStackEntry?.savedStateHandle?.set("selectedCategory", category.strCategory)
                        navController.navigate(Screen.MealsByCategories.route)
                    }
                )
            }

            composable(route = Screen.MealsByCategories.route){
                val viewModel: MealByCategoryViewModel = hiltViewModel()
                    navController.previousBackStackEntry?.savedStateHandle?.get<String>("selectedCategory")?.let { category ->
                    viewModel.getMealsByCategory(category)
                    val meals = viewModel.mealsList.collectAsState().value
                    MealByCategoryScreen(
                        mealList = meals,
                        onMealClick = {mealByCategory ->
                            viewModel.fetchMealDetails(mealByCategory.idMeal)
                        }
                    )

                    val meal by viewModel.meal.collectAsState()
                    LaunchedEffect(meal) {
                        meal?.let {
                            navController.currentBackStackEntry?.savedStateHandle?.set("meal", it)
                            navController.navigate(route = Screen.Details.route)
                            viewModel.resetMealState()
                        }
                    }
                }
            }
        }
    }


}
