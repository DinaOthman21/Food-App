package com.example.food_app.presentation.home.categories


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.food_app.data.remote.dto.categories.Category
import com.example.food_app.presentation.common.CategoryIcon
import com.example.food_app.presentation.home.HomeState

@Composable
fun HomeCategoryListScreen(
    state : HomeState,
    onCategoryClick: (Category)-> Unit
) {
    Text(
        text = "Categories",
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.secondary,
        fontSize = 20.sp,
        modifier = Modifier.padding(start = 30.dp, top = 20.dp)
    )
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(start = 30.dp, end = 30.dp)
            .height(500.dp)
    ) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 8.dp, horizontal = 4.dp),
        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 4.dp)
    ) {
        items(state.categoriesList.size) { index ->
            CategoryIcon(
                category = state.categoriesList[index],
                onCategoryClick = { category->
                    onCategoryClick(category)
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
 }
}
