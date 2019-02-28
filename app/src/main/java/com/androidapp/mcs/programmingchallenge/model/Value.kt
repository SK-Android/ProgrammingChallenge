package com.androidapp.mcs.programmingchallenge.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Value(
    @SerializedName("id") val id: Integer,
    @SerializedName("joke") val joke: String,
    @SerializedName("categories") val categories: List<String>? = null
):Parcelable
