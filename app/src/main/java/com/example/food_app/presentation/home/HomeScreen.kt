package com.example.food_app.presentation.home


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.food_app.data.remote.dto.categories.Category
import com.example.food_app.data.remote.dto.popularMeal.MealByCategory
import com.example.food_app.data.remote.dto.randomMeal.Meal
import com.example.food_app.presentation.common.ErrorScreen
import com.example.food_app.presentation.home.categories.HomeCategoryListScreen
import com.example.food_app.presentation.home.popularItems.HomePopularItemsScreen

@Composable
fun HomeScreen(
    state: HomeState,
    onMealClick:(Meal) -> Unit,
    onPopularItemClick: (MealByCategory)-> Unit,
    onCategoryClick: (Category)-> Unit ,
    onSearchClick:() -> Unit
) {
    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
    ) {

        item {
            HomeHeader(
                onSearchClick = onSearchClick
            )
        }

        item {
            when {
                state.randomIsLoading -> {
                    Text(text = "Random Meal Is Loading...")
                }
                state.randomError != null -> {
                    ErrorScreen(text = "Connect to the Internet")
                    //Text(text = "Error: ${state.randomError}")
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

        item {
            when {
                state.popularIsLoading -> {
                    Text(text = "Popular Items IsLoading...")
                }
                state.popularItems.isNotEmpty() -> {
                    HomePopularItemsScreen(
                        state = state,
                        onItemClick = onPopularItemClick
                    )
                }
            }
        }

        item {
            when {
                state.categoryIsLoading -> {
                    Text(text = "Categories Is Loading...")
                }
                state.categoriesList.isNotEmpty() -> {
                    HomeCategoryListScreen(
                        state = state,
                        onCategoryClick = onCategoryClick
                    )
                }
            }
        }


    }
}
