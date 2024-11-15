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
    }

    private fun fetchRandomMeal() {
        _mealState.value = HomeState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val mealList = mealRepository.getRandomMeal()
                _mealState.value = HomeState(meal = mealList.meals.firstOrNull())
            } catch (e: Exception) {
                e.printStackTrace()
                _mealState.value = HomeState(error = "Failed to load meal: ${e.message}")
            }
        }
    }


}