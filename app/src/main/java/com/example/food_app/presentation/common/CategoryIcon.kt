package com.example.food_app.presentation.common


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.food_app.R
import com.example.food_app.data.remote.dto.categories.Category

@Composable
fun CategoryIcon(
    category : Category,
    onCategoryClick: (String)-> Unit
) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(8.dp)
            .clickable {
                onCategoryClick(category.strCategory)
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = rememberAsyncImagePainter(
                model = category.strCategoryThumb ,
                placeholder = painterResource(id = R.drawable.loadingimage) ,
                error = painterResource(id = R.drawable.errorimage)
            ),
            contentDescription = category.strCategory,
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = category.strCategory,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.primary
            ),
            maxLines = 1
        )
    }
}
