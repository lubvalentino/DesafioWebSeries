package com.example.desafio_web_series.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.desafio_web_series.databinding.ActivityMainBinding
import com.example.desafio_web_series.model.Result
import com.example.desafio_web_series.units.Constants.Intent.KEY_INTENT_DESCRIPTION
import com.example.desafio_web_series.units.Constants.Intent.KEY_INTENT_PATH
import com.example.desafio_web_series.units.Constants.Intent.KEY_INTENT_TITLE
import com.example.desafio_web_series.viewmodel.HomeViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        viewModel.getComics()
        setupObservables()

    }

    private fun setupObservables() {
        viewModel.onResultComics.observe(this,{
            it?.data?.let {
                it -> setupRecylcerView(it.results) }
        })

    }


    private fun setupRecylcerView(comics: List<Result>) {
        binding.rvMarvelHome.apply {
            layoutManager = GridLayoutManager(this@MainActivity,2)
            adapter = HomeAdapter(comics){ positon ->
                val intent = Intent (this@MainActivity, DetailComicActivity::class.java)
                intent.putExtra(KEY_INTENT_TITLE,comics[positon].title)
                intent.putExtra(KEY_INTENT_PATH,comics[positon].thumbnail?.getThumb())
                intent.putExtra(KEY_INTENT_DESCRIPTION, comics[positon].description.toString())
                startActivity(intent)
            }
        }
    }
}

