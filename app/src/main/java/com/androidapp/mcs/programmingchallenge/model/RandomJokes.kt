package com.androidapp.mcs.programmingchallenge.model

import com.google.gson.annotations.SerializedName


data class RandomJokes(
    @SerializedName("type") val type: String,
    @SerializedName("value") val value: Value
) {

}