package com.example.food_app.presentation.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.food_app.R
import com.example.food_app.data.remote.dto.popularMeal.CategoryMeal

@Composable
fun PopularItemCard(
    meal : CategoryMeal ,
    onItemClick : (CategoryMeal)-> Unit
){
    val context = LocalContext.current
        Row(
            modifier = Modifier.clickable { onItemClick(meal) }
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(96.dp)
                    .clip(MaterialTheme.shapes.medium),
               // model = meal.strMealThumb,
                model = ImageRequest.Builder(context).data(meal.strMealThumb).build(),
                contentDescription = "Meal Image",
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.loadingimage),
                error = painterResource(id = R.drawable.errorimage)
            )
        }

}


fun getSampleMeal(): CategoryMeal {
    return CategoryMeal(
        idMeal = "12345",
        strMeal = "Sample Meal",
        strMealThumb = "https://www.example.com/image.jpg"
    )
}

@Preview(showBackground = true)
@Composable
fun PopularItemCardPreview() {
    PopularItemCard(
        meal = getSampleMeal(),
        onItemClick = {}
    )
}