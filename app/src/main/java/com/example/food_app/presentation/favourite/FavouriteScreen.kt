package com.example.food_app.presentation.favourite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.food_app.data.remote.dto.randomMeal.Meal
import com.example.food_app.presentation.common.MealIcon

@Composable
fun FavouriteScreen(
    state : FavouritesScreenState ,
    onMealClick : (Meal)-> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(
                top = 24.dp,
                start = 24.dp,
                end = 24.dp
            )
    ) {

        Text(
            text = "Favourite Meals",
            style = MaterialTheme.typography.displayMedium.copy(
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Default,
                fontSize = 25.sp
            ),
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(18.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2)
            ) {
                items(state.favouriteMeals.size) { position ->
                    MealIcon(
                        meal = state.favouriteMeals[position] ,
                        onMealClick = onMealClick
                    )

                }
            }


    }
}