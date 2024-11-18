package com.example.food_app.data.repository

import coil.network.HttpException
import com.example.food_app.data.remote.ApiServices
import com.example.food_app.data.remote.dto.popularMeal.CategoryMeal
import com.example.food_app.data.remote.dto.randomMeal.MealList
import com.example.food_app.domain.MealRepository
import com.example.food_app.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException


class MealRepositoryImpl (
    private val api : ApiServices
) : MealRepository {

    override suspend fun getRandomMeal(): MealList {
        return api.getRandomMeal()
    }

    override suspend fun getPopularItems(categoryName: String): Flow<Resource<List<CategoryMeal>>> {
        return flow {
            emit(Resource.Loading(true))
            val popularItemsList = try {
                api.getPopularItems(
                    categoryName= categoryName
                )
            } catch (e: IOException){
                e.printStackTrace()
                emit(Resource.Error(message = "Loading Error" , throwable = e))
                return@flow
            }  catch (e : HttpException){
                e.printStackTrace()
                emit(Resource.Error(message = "Loading Error" , throwable = e))
                return@flow
            } catch (e : Exception){
                e.printStackTrace()
                emit(Resource.Error(message = "Loading Error" , throwable = e))
                return@flow
            }
            val popularItems = popularItemsList.meals
            emit(Resource.Success(popularItems))
            emit(Resource.Loading(false))
        }
    }


}