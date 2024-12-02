package com.example.food_app.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.food_app.R
import com.example.food_app.data.remote.dto.randomMeal.Meal

@Composable
fun HomeRandomMeal(
    state: HomeState,
    onMealClick:(Meal) -> Unit
){
    Text(
        text = "What would you like to Eat?",
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        fontSize = 20.sp,
        modifier = Modifier.padding(start = 30.dp, top = 10.dp)
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, end = 30.dp, top = 20.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(data = state.randomMeal!!.strMealThumb).apply(block = fun ImageRequest.Builder.() {
                        error(R.drawable.errorimage)
                        placeholder(R.drawable.loadingimage)
                    }).build()
            ),
            contentDescription = "Meal Image",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clickable {
                    onMealClick(state.randomMeal)
                },
            contentScale = ContentScale.Crop
        )
    }
}