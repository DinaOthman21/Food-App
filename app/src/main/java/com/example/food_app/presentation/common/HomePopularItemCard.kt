package com.example.food_app.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.food_app.R
import com.example.food_app.data.remote.dto.popularMeal.MealByCategory

@Composable
fun HomePopularItemCard(
    meal : MealByCategory,
    onItemClick : (MealByCategory)-> Unit
){
    val context = LocalContext.current
        Row(
            modifier = Modifier.clickable { onItemClick(meal) }
        ) {
            Box(
                modifier = Modifier
                    .size(160.dp)
                    .clip(MaterialTheme.shapes.medium)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(MaterialTheme.shapes.medium),
                    model = ImageRequest.Builder(context).data(meal.strMealThumb).build(),
                    contentDescription = "Meal Image",
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.loadingimage),
                    error = painterResource(id = R.drawable.errorimage)
                )
            }
        }
}
