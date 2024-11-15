package com.example.food_app.presentation.details

import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import com.example.food_app.data.remote.dto.Meal
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.TextStyle
import com.example.food_app.R
import androidx.compose.material3.*
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
@Composable
fun DetailsScreen(
    meal: Meal,
    onFavouriteClick: () -> Unit
) {
    val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(bottom = 64.dp)
        )
        {
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = meal.strMealThumb)
                        .apply(block = fun ImageRequest.Builder.() {
                            error(R.drawable.errorimage)
                            placeholder(R.drawable.loadingimage)
                        }).build()
                ),
                contentDescription = "Meal Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(270.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.category),
                    contentDescription = "Category Icon",
                    modifier = Modifier.padding(start = 5.dp)
                )
                Text(
                    text = "Category : " + meal.strCategory,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    ),
                    modifier = Modifier.padding(start = 8.dp)
                )

                Spacer(modifier = Modifier.width(20.dp))

                Icon(
                    painter = painterResource(id = R.drawable.location),
                    contentDescription = "Location Icon",
                    modifier = Modifier.padding(end = 2.dp)
                )

                Text(
                    text = "Area : " + meal.strArea,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    ),
                    modifier = Modifier.padding(start = 8.dp)
                )

                Box(
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .zIndex(0f)
                        .align(Alignment.CenterVertically)
                ) {
                    Button(
                        onClick = onFavouriteClick,
                        modifier = Modifier,
                        colors = ButtonDefaults.buttonColors()
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.favorite),
                            contentDescription = "Favorite",
                            tint = Color.White
                        )
                    }
                }

            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "- Instructions:",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 16.sp
                )
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = meal.strInstructions,
                color = Color.Black ,
                modifier = Modifier.padding(start = 10.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))
        }



}

