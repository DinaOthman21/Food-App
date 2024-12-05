package com.example.food_app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.food_app.data.remote.dto.randomMeal.Meal

@Database(
    entities = [Meal::class],
    version = 1
)
@TypeConverters(MealTypeConverter::class)
abstract class MealDatabase:RoomDatabase() {
    abstract val mealDao:MealDao
}