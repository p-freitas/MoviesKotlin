package com.example.movieskotlin.presenter

import android.util.Log
import com.example.movieskotlin.R
import com.example.movieskotlin.data.MoviesData
import com.example.movieskotlin.repo.MovieRepository
import com.example.movieskotlin.view.ActivityInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Presenter(private val view: ActivityInterface) : PresenterInterface {
    private val repo = MovieRepository()

    override fun loadMovies() {
        val call = repo.getPopularList()
        call.enqueue(object : retrofit2.Callback<MoviesData> {
            override fun onFailure(call: Call<MoviesData>, t: Throwable) {
                t.message?.let { Log.i("Error ", it) }
            }

            override fun onResponse(call: Call<MoviesData>, response: Response<MoviesData>) {
                val data = response.body()

                if (data != null) {
                    view.displayMovies(ArrayList(data.movies))
                }
            }
        })
    }


    override fun queryMovies(query: String) {
        val call = repo.queryMovies(query)

        call.enqueue(object : Callback<MoviesData> {
            override fun onFailure(call: Call<MoviesData>, t: Throwable) {
                t.message?.let { Log.i("Error ONE Movie: ", it) }
            }

            override fun onResponse(call: Call<MoviesData>, response: Response<MoviesData>) {
                val data = response.body()

                if (data != null) {
                    view.displayMovies(ArrayList(data.movies))
                }
            }
        })
    }
}