package com.example.mvpexample

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvpexample.adapter.RecipeListAdapter
import com.example.mvpexample.api.RetrofitInstance
import com.example.mvpexample.databinding.ActivityMainBinding
import com.example.mvpexample.listeners.Contract
import com.example.mvpexample.listeners.OnRecipeItemClickListener
import com.example.mvpexample.model.Results
import com.example.mvpexample.presenter.Presenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity(), OnRecipeItemClickListener, Contract.View {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: RecipeListAdapter
    private var recipeList = ArrayList<Results>()
    private var presenter: Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpView()
        getRecipes()
    }

    private fun setUpView() {
        binding.rvRecipes.layoutManager = LinearLayoutManager(this)

        presenter = Presenter(this)
        showProgress()
        searchRecipes()
    }

    private fun getRecipes() {
        setData("pasta")
    }

    private fun searchRecipes(){
        binding.btSearch.setOnClickListener {
            val searchString = binding.etSearch.text.toString()
            binding.title.text = searchString
            showProgress()
            setData(searchString)
            binding.etSearch.text.clear()
        }
    }

    override fun onRecipeItemClick(image: String?, title: String?, info: String?) {
        Intent(this, RecipeInfoActivity::class.java).also {
            it.putExtra("image", image)
            it.putExtra("title", title)
            it.putExtra("info", info)
            startActivity(it)
        }
    }

    override fun showProgress() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    override fun setData(query: String) {

        CoroutineScope(Dispatchers.IO).launch {
            val results = presenter?.fetchData(query)
            if (results != null) {
                recipeList = results
            }
            withContext(Dispatchers.Main) {
                adapter = RecipeListAdapter(recipeList, this@MainActivity, this@MainActivity)
                binding.rvRecipes.adapter = adapter
                hideProgress()

            }
        }
    }

}
