package com.example.food_app.presentation.home


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.food_app.data.remote.dto.popularMeal.MealByCategory
import com.example.food_app.data.remote.dto.randomMeal.Meal
import com.example.food_app.presentation.home.categories.CategoryListScreen
import com.example.food_app.presentation.home.popularItems.PopularItemsScreen

@Composable
fun HomeScreen(
    state: HomeState,
    onMealClick:(Meal) -> Unit,
    onPopularItemClick: (MealByCategory)-> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        HomeHeader()
        // random Meal
        when {
            state.randomIsLoading -> {
                Text(text = "Random Meal Is Loading...")
            }
            state.randomError != null -> {
                Text(text = "Error: ${state.randomError}")
            }
            state.randomMeal != null -> {
                    HomeRandomMeal(
                        state = state,
                        onMealClick = onMealClick
                    )
                    PopularItemsScreen(
                        state = state,
                        onItemClick = onPopularItemClick
                    )
            }
            else -> {
                Text(text = "No random meal available.")
            }
        }

        // popular items
        when {
            state.popularIsLoading -> {
                Text(text = "Popular Items IsLoading...")
            }
            state.popularError != null -> {
                Text(text = "Error: ${state.popularError}")
            }
            state.popularItems.isNotEmpty() -> {
                PopularItemsScreen(
                    state = state,
                    onItemClick = onPopularItemClick
                )
            }
            else -> {
                Text(text = "No popular items available.")
            }
        }

        //categories
        when {
            state.categoryIsLoading -> {
                Text(text = "Categories Is Loading...")
            }
            state.categoryError != null -> {
                Text(text = "Error: ${state.categoryError}")
            }
            state.categoriesList.isNotEmpty() -> {
                CategoryListScreen(
                    state = state
                )
            }
            else -> {
                Text(text = "No categories available.")
            }
        }
    }
}
