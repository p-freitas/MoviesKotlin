package com.example.movieskotlin.repo

import com.example.movieskotlin.data.MoviesData
import retrofit2.http.GET
import retrofit2.Call;
import retrofit2.http.Path
import retrofit2.http.Query;

interface MoviesInterface {

    @GET("search/movie")
    fun queryMovies(
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("language") language: String = "pt-BR"

    ): Call<MoviesData>

    @GET("movie/popular")
    fun getPopularList(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "pt-BR"

    ): Call<MoviesData>

    @GET("genre/movie/list")
    fun getGenreList(
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Call<MoviesData>

}
