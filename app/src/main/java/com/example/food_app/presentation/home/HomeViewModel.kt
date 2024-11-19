package com.example.food_app.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food_app.domain.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val mealRepository: MealRepository
) : ViewModel() {

    private val _mealState = MutableStateFlow(HomeState())
    val mealState: StateFlow<HomeState> get() = _mealState

    init {
        fetchRandomMeal()
        getPopularItems()
    }

    private fun fetchRandomMeal() {
        _mealState.value =  _mealState.value.copy(randomIsLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val mealList = mealRepository.getRandomMeal()
                _mealState.value = _mealState.value.copy(
                    randomMeal = mealList.meals.firstOrNull(),
                    randomIsLoading = false
                )
            } catch (e: Exception) {
                e.printStackTrace()
                _mealState.value = _mealState.value.copy(
                    randomError = "Failed to load meal: ${e.message}",
                    randomIsLoading = false
                )
            }
        }
    }

    private fun getPopularItems() {
        _mealState.value = _mealState.value.copy(popularIsLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val popularItems = mealRepository.getPopularItems("Seafood")
                _mealState.value = HomeState(
                    popularItems = popularItems,
                    popularIsLoading = false
                )
            } catch (e: Exception) {
                _mealState.value = HomeState(
                    popularError = e.message ?: "An unknown error occurred",
                    popularIsLoading = false
                )
            }
        }
    }


}