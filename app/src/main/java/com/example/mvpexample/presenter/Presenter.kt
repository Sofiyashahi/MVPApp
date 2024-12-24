package com.example.mvpexample.presenter

import android.content.Context
import android.util.Log
import com.example.mvpexample.api.RetrofitInstance
import com.example.mvpexample.listeners.Contract
import com.example.mvpexample.model.Results

class Presenter(private var mainView: Contract.View?): Contract.Presenter {

    private val apiKey = "397d6063beb7451eb14ba7698570261c"

    override fun onCardClick() {
        if(mainView != null){

        }

    }

    override fun fetchData(query: String): ArrayList<Results>? {
        return try {
            val response = RetrofitInstance.api.getRecipes(apiKey, query, 50, true).execute()
            Log.d("ApiResponse", "fetchData: ${response.body()}")
            if(response.isSuccessful)  response.body()?.results
            else null
        } catch (e: Exception){
            e.printStackTrace()
            null
        }
    }


}