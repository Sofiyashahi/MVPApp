package com.example.mvpexample.model

import android.widget.ImageView
import com.google.gson.annotations.SerializedName

data class Recipes(
    @SerializedName("results") var results      : ArrayList<Results> = arrayListOf(),
    @SerializedName("offset") var offset       : Int?               = null,
    @SerializedName("number") var number       : Int?               = null,
    @SerializedName("totalResults") var totalResults : Int?               = null
)

data class Results (

    @SerializedName("id") var id        : Int?    = null,
    @SerializedName("title") var title     : String? = null,
    @SerializedName("image") var image     : String? = null,
    @SerializedName("imageType") var imageType : String? = null,
    @SerializedName("summary") var summary: String? = null

)