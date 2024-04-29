package com.example.newsarticleapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsarticleapp.R
import com.example.newsarticleapp.model.News
import kotlin.reflect.KFunction1

class ArticleAdapter(private val list: List<News>, private val context: Context, private val itemClicked: KFunction1<String, Unit>) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.news_item_layout,parent,false)
        return ArticleViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val itemView = list[position]
        holder.headline.text = itemView.headLine
        Glide.with(context).load(itemView.imageUrl).into(holder.newsImage)
        holder.description.text = itemView.description

        holder.itemView.rootView.setOnClickListener {
            itemClicked(itemView.url)
            Toast.makeText(context,"Clicked", Toast.LENGTH_SHORT).show()
        }

    }



    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView.rootView){
        val newsImage = itemView.findViewById<ImageView>(R.id.iv_icon)
        val headline = itemView.findViewById<TextView>(R.id.tv_headline)
        val description = itemView.findViewById<TextView>(R.id.tv_description)


    }
}