package com.example.mvpexample.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvpexample.R
import com.example.mvpexample.listeners.OnRecipeItemClickListener
import com.example.mvpexample.model.Recipes
import com.example.mvpexample.model.Results

class RecipeListAdapter(private val recipeList: ArrayList<Results>, private val context: Context, private val recipeItemClickListener: OnRecipeItemClickListener): RecyclerView.Adapter<RecipeListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvTitle : TextView =itemView.findViewById(R.id.tvTitle)
        val image: ImageView = itemView.findViewById(R.id.image)
        val cardView: CardView = itemView.findViewById(R.id.cardView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val items = recipeList[position]

        holder.tvTitle.text = items.title

        Glide.with(context)
            .load(items.image)
            .placeholder(R.drawable.placeholder)
            .into(holder.image)

        holder.cardView.setOnClickListener {
            recipeItemClickListener.onRecipeItemClick(items.image, items.title, items.summary)
        }

    }
}