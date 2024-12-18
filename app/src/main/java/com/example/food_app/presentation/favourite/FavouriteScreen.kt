package com.example.food_app.presentation.favourite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.food_app.R
import com.example.food_app.data.remote.dto.randomMeal.Meal
import com.example.food_app.presentation.common.LottieAnimationShow
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

        if (state.favouriteMeals.isEmpty()) {
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ){
                    LottieAnimationShow(
                        animationResId = R.raw.empty,
                        size = 250,
                        padding = 12,
                        paddingBottom = 0
                    )
                }
                Text(
                    text = "No Favourite Meals",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp
                    ),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        } else
        {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2)
            ) {
                items(state.favouriteMeals.size) { position ->
                    MealIcon(
                        meal = state.favouriteMeals[position],
                        onMealClick = onMealClick
                    )

                }
            }
    }


    }
}