package com.example.movieskotlin.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.movieskotlin.model.Movies
import androidx.recyclerview.widget.RecyclerView
import com.example.movieskotlin.R
import com.example.movieskotlin.model.Genre
import com.example.movieskotlin.view.DetailsActivity
import org.w3c.dom.Text

class Adapter(
    var list: List<Movies>, var context: Context
) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    private var fullList: List<Movies> = ArrayList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.movie_list_item, parent, false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val selected = list[position]
        selected.loadImage(context, holder.img)

        holder.title.text = selected.title
        holder.pontuacao.text = (selected.vote_average).toString()

        holder.card.setOnClickListener {
            val detailsIntent = Intent(context, DetailsActivity::class.java)

            detailsIntent.putExtra("Movies", selected)
            context.startActivity(detailsIntent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var img: ImageView = itemView.findViewById(R.id.movie_img)
        var title: TextView = itemView.findViewById(R.id.movie_title)
        var card: CardView = itemView.findViewById(R.id.card)
        var pontuacao: TextView = itemView.findViewById(R.id.vote_average)
    }

    private var selectedGenre = 28

    fun setGenre(id: Int) {
        selectedGenre = id
        refresh(fullList)
    }

    fun refresh(movies: List<Movies>) {
        list = movies.filter { it.genre.contains(selectedGenre) }
        fullList = ArrayList(movies)

        notifyDataSetChanged()
    }

}