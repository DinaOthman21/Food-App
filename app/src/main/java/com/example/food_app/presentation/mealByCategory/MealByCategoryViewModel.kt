package com.example.food_app.presentation.mealByCategory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food_app.data.remote.dto.popularMeal.MealByCategory
import com.example.food_app.data.remote.dto.randomMeal.Meal
import com.example.food_app.domain.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealByCategoryViewModel @Inject constructor(
    private val mealRepository: MealRepository
) : ViewModel()  {


    private val _mealsList = MutableStateFlow<List<MealByCategory>>(emptyList())
    val mealsList: StateFlow<List<MealByCategory>> get() = _mealsList


    private val _meal = MutableStateFlow<Meal?>(null)
    val meal: StateFlow<Meal?> get() = _meal

    fun getMealsByCategory(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val meals = mealRepository.getMealsByCategory(categoryName = category)
                _mealsList.value = meals
            } catch (e: Exception) {
                e.printStackTrace()
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
            }
        }
    }

    fun resetMealState() {
        _meal.value = null
    }

}