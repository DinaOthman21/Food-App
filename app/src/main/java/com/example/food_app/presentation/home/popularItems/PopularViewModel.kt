package com.example.food_app.presentation.home.popularItems

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
class PopularViewModel @Inject constructor(
    private val mealRepository: MealRepository
) : ViewModel() {

    private val _popularState = MutableStateFlow(PopularState())
    val popularState : StateFlow<PopularState> get() = _popularState

    init {
        getPopularItems()
    }

    private fun getPopularItems() {
        _popularState.value = PopularState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val popularItems = mealRepository.getPopularItems("Seafood")
                _popularState.value = PopularState(
                    popularItems = popularItems,
                    isLoading = false
                )
            } catch (e: Exception) {
                _popularState.value = PopularState(
                    error = e.message ?: "An unknown error occurred",
                    isLoading = false
                )
            }
        }
    }

}