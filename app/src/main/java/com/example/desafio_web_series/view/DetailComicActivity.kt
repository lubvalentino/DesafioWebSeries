package com.example.desafio_web_series.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.desafio_web_series.databinding.ActivityDetailComicBinding
import com.example.desafio_web_series.units.Constants.Intent.KEY_INTENT_DESCRIPTION
import com.example.desafio_web_series.units.Constants.Intent.KEY_INTENT_PATH
import com.example.desafio_web_series.units.Constants.Intent.KEY_INTENT_POPUP
import com.example.desafio_web_series.units.Constants.Intent.KEY_INTENT_TITLE

class DetailComicActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailComicBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailComicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val image = intent.getStringExtra(KEY_INTENT_PATH)
        val title = intent.getStringExtra(KEY_INTENT_TITLE)
        val description = intent.getStringExtra(KEY_INTENT_DESCRIPTION)

        binding.tvComicTitle.text = title
        Glide.with(this).load(image).into(binding.ivDetailPoster)
        if(description == "null"){
            binding.tvComicDescription.text = "Não há descrição sobre este comic"
        } else{
            binding.tvComicDescription.text = description
        }


        binding.ibBackButton.setOnClickListener {
            super.onBackPressed()
            finish()
        }

        binding.ivDetailPoster.setOnClickListener {
            val intent = Intent(this, PopUpImageActivity::class.java)
            intent.putExtra(KEY_INTENT_POPUP,image)
            startActivity(intent)
        }


    }
}