package com.example.movieskotlin.model

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Movies(
    val title: String,
    @SerializedName("genre_ids")
    val genre: List<Int>,
    @SerializedName("poster_path")
    val posterPath: String,
    val overview: String,
    val original_title: String,
    val original_language: String,
    val release_date: String,
    val vote_average: Float
) : Serializable {
    fun loadImage(context: Context, imageView: ImageView) {
        Glide.with(context).load("https://image.tmdb.org/t/p/w500$posterPath").into(imageView)
    }
}
