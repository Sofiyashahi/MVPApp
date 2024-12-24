package com.example.mvpexample

import android.os.Bundle
import android.text.Html
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.mvpexample.databinding.ActivityRecipeInfoBinding

class RecipeInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        binding = ActivityRecipeInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpView()
        binding.back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun setUpView(){
        val name = intent.getStringExtra("title")
        val image = intent.getStringExtra("image")
        val info = intent.getStringExtra("info")

        binding.tvTitle.text = name
        binding.tvInfo.text = Html.fromHtml(info).toString()

        Glide.with(this)
            .load(image)
            .placeholder(R.drawable.placeholder)
            .into(binding.image)
    }
}