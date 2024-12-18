package com.example.food_app.presentation.search

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.food_app.R
import com.example.food_app.data.remote.dto.randomMeal.Meal
import com.example.food_app.presentation.common.LottieAnimationShow
import com.example.food_app.presentation.common.MealIcon
import com.example.food_app.presentation.common.SearchBar

@Composable
fun SearchScreen(
    searchViewModel: SearchViewModel ,
    onMealClick:(Meal) -> Unit
){
    val state by searchViewModel.state
    val meals = state.meals

    Column(
        modifier = Modifier
            .padding(top = 24.dp, start = 24.dp, end = 24.dp)
            .statusBarsPadding()
            .fillMaxSize()
    ) {
        SearchBar(
            searchQuery = state.searchQuery,
            onSearchQueryChange = { searchViewModel.setSearchQuery(it)} ,
            onSearch = {searchViewModel.searchMeals()}
        )

        if (!meals.isNullOrEmpty()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2)
            ) {
                items(meals.size) { position ->
                    MealIcon(
                        meal = meals[position],
                        onMealClick = onMealClick
                    )
                }
            }
        }
        else{
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    LottieAnimationShow(
                        animationResId = R.raw.search,
                        size = 250,
                        padding = 12,
                        paddingBottom = 0
                    )
                }
                Text(
                    text = "Search For Meal",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp
                    ),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }

    }
}