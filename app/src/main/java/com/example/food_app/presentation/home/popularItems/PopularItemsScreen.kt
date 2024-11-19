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

@Composable
fun PopularItemsScreen(
    state : PopularState ,
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
        if(state.isLoading) CircularProgressIndicator()
        if(state.error!= null) Text(state.error)
    }
}