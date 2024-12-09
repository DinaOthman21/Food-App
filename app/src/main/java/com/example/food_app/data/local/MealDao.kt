package com.example.food_app.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.food_app.data.remote.dto.randomMeal.Meal

@Dao
interface MealDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertMeal(meal: Meal)

    @Delete
    suspend fun deleteMeal(meal:Meal)

    @Query("SELECT * FROM mealtable")
    fun getAllMeals(): List<Meal>
}