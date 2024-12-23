package com.example.food_app.presentation.search

import androidx.lifecycle.ViewModel
import com.example.food_app.domain.MealRepository
import javax.inject.Inject
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val mealRepository: MealRepository
) : ViewModel() {

    private var _state = mutableStateOf(SearchState())
    val state : State<SearchState> = _state

    fun setSearchQuery(searchQuery: String) {
        _state.value = state.value.copy(searchQuery = searchQuery)
    }

    fun searchMeals(){
        viewModelScope.launch(Dispatchers.IO) {
            if (state.value.searchQuery.isBlank()) {
                _state.value = _state.value.copy(
                    meals = emptyList(),
                    searchError = "Please enter a search query.",
                    isLoading = false
                )
                return@launch
            }
            _state.value = _state.value.copy(
                isLoading = true,
                searchError = null
            )
            try {
                val meals = mealRepository.searchMeals(searchQuery = state.value.searchQuery)
                _state.value = _state.value.copy(
                    isLoading = false,
                    meals = meals
                )
            } catch (e: Exception) {
                e.printStackTrace()
                _state.value = _state.value.copy(
                    isLoading = false ,
                    searchError = "Failed to search meals: ${e.message}",
                )
            }
        }
    }

}