package com.example.food_app.presentation.home.popularItems

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.food_app.data.remote.dto.popularMeal.CategoryMeal
import com.example.food_app.presentation.common.PopularItemCard
import com.example.food_app.presentation.home.HomeState

@Composable
fun PopularItemsScreen(
    state : HomeState ,
    onItemClick : (CategoryMeal) -> Unit
){
    Box (
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    )
    {
        LazyRow (
            modifier = Modifier.fillMaxSize()
        ) {
            items(state.popularItems){ item ->
                PopularItemCard(
                    meal = item ,
                    onItemClick = { onItemClick(item)}
                )
            }
        }
        if(state.popularIsLoading) CircularProgressIndicator()
        if(state.popularError!= null) Text(state.popularError)
    }
}