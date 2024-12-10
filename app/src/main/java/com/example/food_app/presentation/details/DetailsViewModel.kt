package com.example.food_app.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.food_app.data.remote.dto.randomMeal.Meal
import com.example.food_app.domain.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val mealRepository: MealRepository
) : ViewModel() {

    fun onFavouriteClick(meal: Meal){
        viewModelScope.launch(Dispatchers.IO) {
            mealRepository.upsertMeal(meal = meal)
        }
    }
}