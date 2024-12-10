package com.example.food_app.presentation.favourite

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.food_app.domain.MealRepository
import javax.inject.Inject
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val mealRepository: MealRepository
) : ViewModel() {

    private val _favouritesSate = mutableStateOf(FavouritesScreenState())
    val favouritesState : State<FavouritesScreenState> = _favouritesSate

    init {
        getFavouritesMeals()
    }

    private fun getFavouritesMeals() {
        viewModelScope.launch(Dispatchers.IO) {
                val meals = mealRepository.getAllMeals()
                _favouritesSate.value = favouritesState.value.copy(
                    favouriteMeals = meals
                )
        }
    }
}