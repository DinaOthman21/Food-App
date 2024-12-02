package com.example.food_app.presentation.home.categories


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.food_app.presentation.common.CategoryIcon
import com.example.food_app.presentation.home.HomeState

@Composable
fun CategoryListScreen(
    state : HomeState
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.padding(start = 30.dp, end = 30.dp).fillMaxSize()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 8.dp, horizontal = 4.dp),
            contentPadding = PaddingValues(vertical = 8.dp, horizontal = 4.dp)
        ) {
            items(state.categoriesList.size) { index ->
                CategoryIcon(state.categoriesList[index])
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
