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

    private val _meal = MutableStateFlow<Meal?>(null)
    val meal: StateFlow<Meal?> get() = _meal


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
                    randomIsLoading = false,
                    randomError = null
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
                _homeState.value = _homeState.value.copy(
                    popularItems = popularItems,
                    popularIsLoading = false,
                    popularError = null
                )
            } catch (e: Exception) {
                e.printStackTrace()
                _homeState.value = _homeState.value.copy(
                    popularError = "Failed to load popular items: ${e.message}",
                    popularIsLoading = false
                )
            }
        }
    }

    fun fetchMealDetails(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val meal = mealRepository.getMealDetails(id)
                _meal.value = meal
            } catch (e: Exception) {
                e.printStackTrace()
                _meal.value = null
            }
        }
    }

    fun resetMealState() {
        _meal.value = null
    }

    fun getCategoriesList() {
        _homeState.value = _homeState.value.copy(categoryIsLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val categoryList = mealRepository.getCategories()
                _homeState.value = _homeState.value.copy(
                    categoriesList = categoryList.categories,
                    categoryIsLoading = false,
                    categoryError = null
                )
            } catch (e: Exception) {
                e.printStackTrace()
                _homeState.value = _homeState.value.copy(
                    categoryError = "Failed to load categories: ${e.message}",
                    categoryIsLoading = false
                )
            }
        }
    }
}


