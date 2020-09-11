package com.example.movieskotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.inputmethod.EditorInfo
import android.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieskotlin.R
import com.example.movieskotlin.model.Movies
import com.example.movieskotlin.presenter.Presenter
import com.example.movieskotlin.presenter.PresenterInterface
import com.example.movieskotlin.adapter.Adapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ActivityInterface {

    lateinit var adapter: Adapter
    lateinit var presenterInterface: PresenterInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenterInterface = Presenter(this)
        presenterInterface.loadMovies()

        adapter = Adapter(ArrayList(), this)
        recyclerView.adapter = adapter

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                var genre = 28

                when (tab.position) {
                    0 -> genre = 28
                    1 -> genre = 18
                    2 -> genre = 14
                    3 -> genre = 878
                }
                adapter.setGenre(genre)
            }
        })

        supportActionBar?.elevation = 0f

        val gridLayoutManager = GridLayoutManager(applicationContext, 2)
        recyclerView.layoutManager = gridLayoutManager

    }

    override fun displayMovies(movies: ArrayList<Movies>) {
        adapter.refresh(movies)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val item = menu.findItem(R.id.searchAction)
        val view = item.actionView as SearchView

        view.imeOptions = EditorInfo.IME_ACTION_DONE

        view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                presenterInterface.queryMovies(query)
                return false
            }

            override fun onQueryTextChange(Text: String): Boolean {
                presenterInterface.queryMovies(Text)
                return false
            }
        })
        return true
    }


}