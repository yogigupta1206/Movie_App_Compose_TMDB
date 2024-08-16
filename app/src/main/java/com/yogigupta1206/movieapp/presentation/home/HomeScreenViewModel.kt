package com.yogigupta1206.movieapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yogigupta1206.movieapp.domain.model.MovieEntity
import com.yogigupta1206.movieapp.domain.repository.MovieRepo
import com.yogigupta1206.movieapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repository: MovieRepo
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState: StateFlow<HomeScreenUiState> = _uiState.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText: StateFlow<String> = _searchText.asStateFlow()
    private var job: Job? = null

    private var allMovies: List<MovieEntity> = emptyList()

    init {
        fetchData()
    }

    fun fetchData() {
        if (job?.isActive == true) {
            job?.cancel()
        }
        job = viewModelScope.launch {
            repository.getMovieData().collect {
                    when (it) {
                        is Resource.Error -> {
                            _uiState.value =
                                _uiState.value.copy(isLoading = false, errorMessage = it.message)
                        }

                        is Resource.Loading -> {
                            _uiState.value = _uiState.value.copy(isLoading = true)
                        }

                        is Resource.Success -> {
                            allMovies = it.data?.results ?: emptyList()
                            updateUiState(allMovies)
                        }
                    }
                }
        }
    }

    fun onSearchTextChange(newText: String) {
        _searchText.value = newText
        updateUiState(allMovies)
    }

    private fun updateUiState(movies: List<MovieEntity>) {
        viewModelScope.launch(Dispatchers.Default) {
            val filteredMovies = filterMovies(movies)
            withContext(Dispatchers.Main) {
                _uiState.value = _uiState.value.copy(
                    movieData = filteredMovies,
                    isLoading = false,
                    errorMessage = null
                )
            }
        }
    }

    private fun filterMovies(movies: List<MovieEntity>): List<MovieEntity> {
        val searchTerm = searchText.value.lowercase()
        return if (searchTerm.isEmpty()) {
            movies
        } else {
            movies.filter { movie -> movie.title.lowercase().contains(searchTerm) }
        }
    }

}
