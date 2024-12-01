package com.example.food_app.presentation.home


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.food_app.R
import com.example.food_app.data.remote.dto.popularMeal.MealByCategory
import com.example.food_app.data.remote.dto.randomMeal.Meal
import com.example.food_app.presentation.home.popularItems.PopularItemsScreen

@Composable
fun HomeScreen(
    state: HomeState,
    onMealClick:(Meal) -> Unit,
    onPopularItemClick: (MealByCategory)-> Unit
) {

    when {
        state.randomIsLoading -> {
            Text(text = "Loading...")
        }
        state.randomError != null -> {
            Text(text = "Error: ${state.randomError}")
        }
        state.randomMeal != null -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Row(
                    modifier = Modifier
                        .padding(start = 30.dp, end = 20.dp, top = 10.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Home",
                        fontFamily = FontFamily.Default,
                        fontWeight = FontWeight.Bold,
                        color = Color.Red,
                        fontSize = 25.sp,
                        modifier = Modifier.weight(3f)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.search),
                        contentDescription = "Search Icon",
                        modifier = Modifier.size(40.dp)
                    )
                }
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
                                .data(data = state.randomMeal.strMealThumb).apply(block = fun ImageRequest.Builder.() {
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

                Text(
                    text = "Popular Items",
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 30.dp, top = 20.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                PopularItemsScreen(
                    state = state ,
                    onItemClick = onPopularItemClick
                )
            }
        }
        else -> {
            Text(text = "No data available.")
        }
    }
}
