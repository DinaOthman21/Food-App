package com.example.food_app.presentation.categories

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.food_app.data.remote.dto.categories.Category
import com.example.food_app.presentation.common.CategoryIcon

@Composable
fun CategoriesScreen(
    categoriesList :List<Category>,
    onCategoryClick: (Category)-> Unit
) {
    if (categoriesList.isEmpty()) {
        Text(
            text = "No Categories Found",
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp
            ),
            color = MaterialTheme.colorScheme.onSurface
        )
    } else{
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 8.dp, horizontal = 4.dp),
            contentPadding = PaddingValues(vertical = 8.dp, horizontal = 4.dp)
        ) {
            items(categoriesList.size) { index ->
                CategoryIcon(
                    category = categoriesList[index],
                    onCategoryClick = { category ->
                        onCategoryClick(category)
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
}
}