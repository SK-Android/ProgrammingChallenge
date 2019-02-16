package com.androidapp.mcs.programmingchallenge.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Value(
    @SerializedName("id") val id: Integer,
    @SerializedName("joke") val joke: String,
    @SerializedName("categories") val categories: List<Objects>? = null
)
