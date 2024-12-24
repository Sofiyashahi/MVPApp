package com.example.mvpexample.api

import com.example.mvpexample.model.Recipes
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RecipeApi {

    @Headers("Content-Type: application/json")
    @GET("recipes/complexSearch")
    fun getRecipes(
        @Query("apiKey") apiKey: String,
        @Query("query") query: String,
        @Query("number") number: Int,
        @Query("addRecipeInformation") info: Boolean
    ): Call<Recipes>
}