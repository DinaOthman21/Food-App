package com.example.food_app.di

import android.app.Application
import androidx.room.Room
import com.example.food_app.data.local.MealDao
import com.example.food_app.data.local.MealDatabase
import com.example.food_app.data.remote.ApiServices
import com.example.food_app.data.repository.MealRepositoryImpl
import com.example.food_app.domain.MealRepository
import com.example.food_app.util.Constants.BASE_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

  private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    fun provideApiServices(): ApiServices {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(ApiServices::class.java)
    }

    @Provides
    @Singleton
    fun provideMealRepository(
        apiServices: ApiServices
    ) : MealRepository = MealRepositoryImpl(api = apiServices)

    @Provides
    @Singleton
    fun provideMealDatabase(
        application: Application
    ) : MealDatabase{
        return Room.databaseBuilder(
           context =  application ,
           klass =  MealDatabase::class.java,
           name =  "meal_db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideMealDao(
        mealDatabase: MealDatabase
    ):MealDao = mealDatabase.mealDao


}