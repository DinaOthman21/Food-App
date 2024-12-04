package com.example.food_app.presentation.mealByCategory

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food_app.data.remote.dto.popularMeal.MealByCategory
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


    private val _categoriesList = MutableStateFlow<List<MealByCategory>>(emptyList())
    val categoriesList: StateFlow<List<MealByCategory>> get() = _categoriesList

    fun getMealsByCategory(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("HomeViewModel", "Fetching meals for category: $category")
            try {
                val categories = mealRepository.getMealsByCategory(categoryName = category)
                _categoriesList.value = categories
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("HomeViewModel", "Error fetching meals for category $category: ${e.message}")
            }
        }

    }

}