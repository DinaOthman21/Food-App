package com.example.food_app.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.food_app.R

@Composable
fun HomeHeader(
    onSearchClick:() -> Unit
){
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
            color = MaterialTheme.colorScheme.primary,
            fontSize = 25.sp,
            modifier = Modifier.weight(3f)
        )
        Image(
            painter = painterResource(id = R.drawable.search),
            contentDescription = "Search Icon",
            modifier = Modifier.size(40.dp).clickable {
                onSearchClick()
            } ,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
        )
    }
}