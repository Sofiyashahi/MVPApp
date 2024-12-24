package com.example.mvpexample.listeners

import com.example.mvpexample.model.Results

interface Contract {

    interface View {
        fun showProgress()

        fun hideProgress()

        fun setData(query: String)
    }

    interface Presenter {
        fun onCardClick()

        fun fetchData(query: String): ArrayList<Results>?
    }
}