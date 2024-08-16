package com.yogigupta1206.movieapp.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yogigupta1206.movieapp.presentation.home.components.MovieItem

@Composable
fun HomeScreen(
    onNavigateToMovieDetails: (Int) -> Unit,
    viewModel: HomeScreenViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsState()
    val searchText by viewModel.searchText.collectAsState()

    Scaffold(
        content = { padding ->
            HomeScreenContent(
                padding,
                uiState,
                searchText,
                {newText ->
                    viewModel.onSearchTextChange(newText)
                },
                onNavigateToMovieDetails,
                viewModel::fetchData
            )
        }
    )
}

@Composable
fun HomeScreenContent(
    padding: PaddingValues,
    uiState: HomeScreenUiState,
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    onNavigateToMovieDetails: (Int) -> Unit,
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier.padding(padding)
    ) {
        SearchHeader(
            searchText = searchText,
            onValueChange = onSearchTextChange
        )

        when {
            uiState.isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            !uiState.errorMessage.isNullOrBlank() && uiState.movieData.isEmpty()-> {
                ErrorView(message = uiState.errorMessage, onRetry = onRetry)
            }
            else ->{
                LazyVerticalGrid(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .padding(top = 30.dp),
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(17.dp),
                    horizontalArrangement = Arrangement.spacedBy(17.dp)
                ) {
                    items(uiState.movieData){
                        MovieItem(it, onClick = {
                            onNavigateToMovieDetails(it.id)
                        })
                    }
                }
            }
        }
    }
}

@Composable
fun ErrorView(message: String, onRetry: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = message, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(horizontal = 16.dp))
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onRetry) {
            Text("Retry")
        }
    }
}

@Composable
fun SearchHeader(
    searchText: String,
    onValueChange: (String) -> Unit,
) {
    SearchBar(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 24.dp),
        query = searchText,
        onQueryChange = onValueChange,
    )
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        label = { Text("Search movies", color = Color(0xFF9AA4B2)) },
        modifier = modifier
            .fillMaxWidth(),
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Search,
                contentDescription = "Search movies",
                tint = Color(0xFF9AA4B2))
        },
        singleLine = true,
        shape = RoundedCornerShape(15),
        colors = OutlinedTextFieldDefaults.colors().copy(focusedPlaceholderColor = Color(0xFF9AA4B2), unfocusedPlaceholderColor = Color(0xFF9AA4B2))
    )
}
