package com.example.desafio_web_series.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(
    val extension: String,
    val path: String
):Parcelable