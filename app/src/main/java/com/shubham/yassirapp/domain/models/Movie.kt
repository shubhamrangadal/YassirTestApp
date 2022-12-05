package com.shubham.yassirapp.domain.models


import com.google.gson.annotations.SerializedName

data class Movie(

    @SerializedName("results")
    val results: List<MovieList>

    )