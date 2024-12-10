package com.example.food_app.presentation.home


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.food_app.data.remote.dto.categories.Category
import com.example.food_app.data.remote.dto.popularMeal.MealByCategory
import com.example.food_app.data.remote.dto.randomMeal.Meal
import com.example.food_app.presentation.home.categories.HomeCategoryListScreen
import com.example.food_app.presentation.home.popularItems.HomePopularItemsScreen

@Composable
fun HomeScreen(
    state: HomeState,
    onMealClick:(Meal) -> Unit,
    onPopularItemClick: (MealByCategory)-> Unit,
    onCategoryClick: (Category)-> Unit
) {
    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            HomeHeader()
        }

        // random Meal
        item {
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
                }
                else -> {
                    Text(text = "No random meal available.")
                }
            }
        }

        // popular items
        item {
            when {
                state.popularIsLoading -> {
                    Text(text = "Popular Items IsLoading...")
                }
                state.popularError != null -> {
                    Text(text = "Error: ${state.popularError}")
                }
                state.popularItems.isNotEmpty() -> {
                    HomePopularItemsScreen(
                        state = state,
                        onItemClick = onPopularItemClick
                    )
                }
                else -> {
                    Text(text = "No popular items available.")
                }
            }
        }

        //categories
        item {
            when {
                state.categoryIsLoading -> {
                    Text(text = "Categories Is Loading...")
                }
                state.categoryError != null -> {
                    Text(text = "Error: ${state.categoryError}")
                }
                state.categoriesList.isNotEmpty() -> {
                    HomeCategoryListScreen(
                        state = state,
                        onCategoryClick = onCategoryClick
                    )
                }
                else -> {
                    Text(text = "No categories available.")
                }
            }
        }


    }
}
