package com.example.food_app.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.food_app.data.remote.dto.randomMeal.Meal

@Composable
fun FavouritesScreenIcon(
    meal : Meal
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .size(200.dp)
                .padding(8.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(meal.strMealThumb),
                contentDescription = meal.strMeal,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = meal.strMeal?:"",
            fontFamily = FontFamily.Default,
            color = Color.Black,
            modifier = Modifier.padding(top = 4.dp),
            fontSize = 15.sp
        )
    }
}