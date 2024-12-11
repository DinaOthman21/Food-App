package com.example.food_app.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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

    var sideEffect by mutableStateOf<String?>(null)
        private set

    fun onFavouriteClick(meal: Meal){
        viewModelScope.launch(Dispatchers.IO) {
            val mealFromDB = mealRepository.getMealFromDB(mealId = meal.idMeal)
            if(mealFromDB==null){
                mealRepository.upsertMeal(meal = meal)
                sideEffect="Added To Favourites"
            } else{
                mealRepository.deleteMeal(meal = meal)
                sideEffect="Removed From Favourites"
            }
        }
    }

    fun removeSideEffect() {
        sideEffect = null
    }

}