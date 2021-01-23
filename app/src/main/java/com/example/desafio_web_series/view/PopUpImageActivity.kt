package com.example.desafio_web_series.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.desafio_web_series.databinding.ActivityPopUpImageBinding
import com.example.desafio_web_series.units.Constants.Intent.KEY_INTENT_POPUP

class PopUpImageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPopUpImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPopUpImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val popupImage = intent.getStringExtra(KEY_INTENT_POPUP)

        Glide.with(this).load(popupImage).into(binding.ivPopupPoster)

        binding.ivPopupPoster.setOnClickListener {
            finish()
        }
    }
}