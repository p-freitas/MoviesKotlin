package com.example.movieskotlin.repo

import com.example.movieskotlin.data.MoviesData
import com.example.movieskotlin.utils.API_KEY
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.movieskotlin.utils.API_URL
import retrofit2.Call

class MovieRepository : MoviesInterface {

    private val service: MoviesInterface

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(MoviesInterface::class.java)
    }

    override fun queryMovies(apiKey: String, query: String, language: String): Call<MoviesData> {
        return service.queryMovies(apiKey, query, "pt-BR")
    }

    fun queryMovies(query: String): Call<MoviesData> {
        return if (query.isEmpty()) {
            getPopularList()
        } else {
            queryMovies(API_KEY, query, "pt-BR")
        }
    }

    override fun getPopularList(apiKey: String, language: String): Call<MoviesData> {
        return service.getPopularList(apiKey, language)
    }

    fun getPopularList(): Call<MoviesData> {
        return service.getPopularList(API_KEY, "pt-BR")
    }

    override fun getGenreList(apiKey: String, language: String): Call<MoviesData> {
        return service.getGenreList(API_KEY, "pt-BR")
    }
}