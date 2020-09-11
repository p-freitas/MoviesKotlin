package com.example.movieskotlin.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movieskotlin.R
import com.example.movieskotlin.model.Movies
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movies = intent.getSerializableExtra("Movies") as Movies

        setDetails(movies)
    }

    private fun setDetails(movies: Movies) {
        movie_title.text = movies.title
        movie_details.text = movies.overview
        movies.loadImage(this, movie_img)
        tituloOriginal.text = movies.original_title
        idiomaOriginal.text = movies.original_language
        dataLancamento.text = movies.release_date
        pontuacao.text = (movies.vote_average).toString()

        supportActionBar?.title = movies.title

    }

    override fun onSupportNavigateUp(): Boolean {
        finish()

        return true
    }
}