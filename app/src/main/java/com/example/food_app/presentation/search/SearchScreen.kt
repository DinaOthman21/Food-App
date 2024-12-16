package com.example.food_app.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.food_app.data.remote.dto.randomMeal.Meal
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

        Spacer(modifier = Modifier.height(24.dp))

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

    }
}