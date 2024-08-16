package com.yogigupta1206.movieapp.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.yogigupta1206.movieapp.R
import com.yogigupta1206.movieapp.domain.model.MovieEntity
import com.yogigupta1206.movieapp.utils.IMAGE_BASE_URL
import com.yogigupta1206.movieapp.utils.IMAGE_SIZE_W185
import kotlinx.coroutines.Dispatchers

@Composable
fun MovieItem(movie: MovieEntity, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clickable { onClick() }
    ) {
        Column{
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(IMAGE_BASE_URL + IMAGE_SIZE_W185 + movie.posterPath)
                    .crossfade(true)
                    .dispatcher(Dispatchers.IO)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .transformations(RoundedCornersTransformation())
                    .networkCachePolicy(CachePolicy.ENABLED)
                    .build(),
                contentDescription = movie.title,
                modifier = Modifier
                    .aspectRatio(1F)
                    .clip(RoundedCornerShape(5)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = movie.title,
                lineHeight = 24.sp,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MovieItemPreview() {

    MovieItem(
        MovieEntity(
            id = 1,
            title = "Movie Title",
            overview = "Movie Overview, movie discussion",
            posterPath = "/7mQ13AV4qd6A43XxXfSoyYQMzhh.jpg"
        )
    ) {

    }
    /*MovieAppTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            AppEntryPoint()
        }
    }*/
}