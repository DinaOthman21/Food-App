package com.example.food_app.presentation.home.popularItems

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.food_app.data.remote.dto.popularMeal.MealByCategory
import com.example.food_app.presentation.common.HomePopularItemCard
import com.example.food_app.presentation.home.HomeState

@Composable
fun HomePopularItemsScreen(
    state : HomeState,
    onItemClick : (MealByCategory) -> Unit
){
    Text(
        text = "Popular Items",
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.secondary,
        fontSize = 20.sp,
        modifier = Modifier.padding(start = 30.dp, top = 20.dp)
    )

    Spacer(modifier = Modifier.height(16.dp))

    Box (
        contentAlignment = Alignment.Center,
        modifier = Modifier.padding(start = 30.dp, end = 30.dp).fillMaxSize()
    )
    {
        LazyRow (
            modifier = Modifier.fillMaxSize() ,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(state.popularItems){ item ->
                HomePopularItemCard(
                    meal = item ,
                    onItemClick = { onItemClick(item)}
                )
            }
        }

    }
}