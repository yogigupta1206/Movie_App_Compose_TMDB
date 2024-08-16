package com.yogigupta1206.movieapp.presentation.movie_info

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.yogigupta1206.movieapp.R
import com.yogigupta1206.movieapp.domain.model.MovieEntity
import com.yogigupta1206.movieapp.presentation.CommonViewModel
import com.yogigupta1206.movieapp.utils.IMAGE_BASE_URL
import com.yogigupta1206.movieapp.utils.IMAGE_SIZE_W500
import kotlinx.coroutines.Dispatchers

@Composable
fun MovieDetailsScreen(
    viewModel: CommonViewModel = hiltViewModel(),
    onNavigateBack:() -> Unit
) {

    val state = viewModel.movieDetails.value

    if (state?.isLoading == true) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else if (!state?.error.isNullOrBlank()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = state?.error.toString(), color = Color.Red)
        }
    } else {
        state?.movie?.let { MovieDetailsContent(it, onNavigateBack) }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsContent(movie: MovieEntity, onNavigateBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("") },
                navigationIcon = {
                    IconButton(onClick = { onNavigateBack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(top = 24.dp, start = 16.dp, end = 16.dp)
                .fillMaxSize()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(IMAGE_BASE_URL + IMAGE_SIZE_W500 + movie.posterPath)
                    .crossfade(true)
                    .dispatcher(Dispatchers.IO)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .transformations(RoundedCornersTransformation())
                    .networkCachePolicy(CachePolicy.ENABLED)
                    .build(),
                contentDescription = movie.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1F)
                    .clip(RoundedCornerShape(3)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = movie.title,
                lineHeight = 32.sp,
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = movie.overview,
                lineHeight = 24.sp,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Start
            )
        }
    }
}