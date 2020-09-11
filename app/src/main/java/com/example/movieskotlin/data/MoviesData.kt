package com.example.movieskotlin.data

import com.example.movieskotlin.model.Genre
import com.example.movieskotlin.model.Movies
import com.google.gson.annotations.SerializedName

data class MoviesData(@SerializedName("results") val movies: List<Movies>)

data class GenreData(@SerializedName("genres") val genres: List<Genre>)