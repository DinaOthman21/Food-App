package com.example.food_app.presentation.mealByCategory

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.food_app.data.remote.dto.popularMeal.MealByCategory

@Composable
fun MealByCategoryScreen(
    mealList : List<MealByCategory>
) {
    if (mealList.isEmpty()) {
        Text(text = "No categories available")
    } else {
        LazyColumn {
            items(mealList.size) { category ->
                Text(text = mealList[category].strMeal)
            }
        }
    }
}