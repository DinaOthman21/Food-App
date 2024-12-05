package com.example.food_app.data.local

import androidx.room.TypeConverter
import androidx.room.TypeConverters

@TypeConverters
class MealTypeConverter {
    @TypeConverter
    fun fromAnyToString(any: Any?):String{
        if(any == null){
            return ""
        }
        return any as String
    }

    @TypeConverter
    fun fromStringToAny(string: String?) : Any{
        if(string==null){
            return ""
        }
        return string
    }
}