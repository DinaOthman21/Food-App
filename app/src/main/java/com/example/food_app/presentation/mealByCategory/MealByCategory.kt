package com.example.food_app.presentation.mealByCategory

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.food_app.data.remote.dto.popularMeal.MealByCategory
import com.example.food_app.presentation.common.MealByCategoryIcon

@Composable
fun MealByCategoryScreen(
    mealList : List<MealByCategory>
) {
    if (mealList.isEmpty()) {
        Text(text = "No Meals available")
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ) {
            items(mealList.size) { category ->
                MealByCategoryIcon(
                    category = mealList[category]
                )
            }
        }
    }
}