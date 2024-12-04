package com.example.food_app.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food_app.data.remote.dto.randomMeal.Meal
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

    private val _homeState = MutableStateFlow(HomeState())
    val homeState: StateFlow<HomeState> get() = _homeState

    private val _mealDetailsState = MutableStateFlow<Meal?>(null)
    val mealDetailsState: StateFlow<Meal?> get() = _mealDetailsState

    private val _navigateToDetails = MutableStateFlow(false)
    val navigateToDetails: StateFlow<Boolean> get() = _navigateToDetails


    init {
        fetchRandomMeal()
        getPopularItems()
        getCategoriesList()
    }

    private fun fetchRandomMeal() {
        _homeState.value = _homeState.value.copy(randomIsLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val mealList = mealRepository.getRandomMeal()
                _homeState.value = _homeState.value.copy(
                    randomMeal = mealList.meals.firstOrNull(),
                    randomIsLoading = false
                )
            } catch (e: Exception) {
                e.printStackTrace()
                _homeState.value = _homeState.value.copy(
                    randomError = "Failed to load meal: ${e.message}",
                    randomIsLoading = false
                )
            }
        }
    }

    private fun getPopularItems() {
        _homeState.value = _homeState.value.copy(popularIsLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val popularItems = mealRepository.getPopularItems("Seafood")
                _homeState.value = HomeState(
                    popularItems = popularItems,
                    popularIsLoading = false
                )
            } catch (e: Exception) {
                _homeState.value = HomeState(
                    popularError = e.message ?: "An unknown error occurred",
                    popularIsLoading = false
                )
            }
        }
    }

    fun fetchMealDetails(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val meal = mealRepository.getMealDetails(id)
                _mealDetailsState.value = meal
                _navigateToDetails.value = true
            } catch (e: Exception) {
                e.printStackTrace()
                _mealDetailsState.value = null
                _homeState.value =
                    _homeState.value.copy(randomError = "Failed to load meal details: ${e.message}")
            }
        }
    }

    fun resetNavigationState() {
        _navigateToDetails.value = false
    }

    private fun getCategoriesList() {
        _homeState.value = _homeState.value.copy(categoryIsLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val categoryList = mealRepository.getCategories()
                _homeState.value = _homeState.value.copy(
                    categoriesList = categoryList.categories,
                    categoryIsLoading = false
                )
            } catch (e: Exception) {
                e.printStackTrace()
                _homeState.value = _homeState.value.copy(
                    categoryError = "Failed to load category: ${e.message}",
                    categoryIsLoading = false
                )
            }
        }
    }


}