package com.example.food_app.presentation.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.food_app.R
import com.example.food_app.data.remote.dto.randomMeal.Meal

@Composable
fun DetailsScreen(
    meal: Meal,
    onFavouriteClick: () -> Unit,
    onWatchClick: () -> Unit
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
                    modifier = Modifier.padding(start = 2.dp)
                )

                Spacer(modifier = Modifier.width(35.dp))

                Icon(
                    painter = painterResource(id = R.drawable.location),
                    contentDescription = "Location Icon"
                )

                Text(
                    text = "Area : " + meal.strArea,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                )

                Spacer(modifier = Modifier.weight(1f))

                Box(
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp)
                        .zIndex(0f)
                        .align(Alignment.CenterVertically)
                ) {
                    Button(
                        onClick = { onFavouriteClick() } ,
                        colors = ButtonDefaults.buttonColors(
                           containerColor = Color.Red ,
                            contentColor = Color.White
                        )
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
                    fontSize = 18.sp
                )
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = meal.strInstructions,
                color = Color.Black ,
                modifier = Modifier.padding(start = 10.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {  onWatchClick()
                       /* // Intent to open YouTube URL
                        val context = LocalContext.current
                        val intent = android.content.Intent(
                            android.content.Intent.ACTION_VIEW,
                            android.net.Uri.parse(meal.strYoutube)
                        )
                        context.startActivity(intent)*/
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red
                    )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.youtube),
                        contentDescription = "Watch on YouTube",
                        tint = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Watch on YouTube",
                        color = Color.White
                    )
                }
            }
        }



}
