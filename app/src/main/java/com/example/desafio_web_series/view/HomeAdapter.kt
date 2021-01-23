package com.example.desafio_web_series.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.desafio_web_series.databinding.MainItemHqBinding
import com.example.desafio_web_series.model.Result


class HomeAdapter (
    private val comicsList: List<Result>,
    private val onItemClicked: (Int) -> Unit
        ):RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = MainItemHqBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        holder.bind(comicsList[position], onItemClicked)
    }

    override fun getItemCount(): Int {
       return comicsList.size
    }

    class ViewHolder (
        private val binding: MainItemHqBinding
            ):RecyclerView.ViewHolder(binding.root){
                fun bind (comics: Result, onItemClicked: (Int) -> Unit):Unit = with(itemView){
                    Glide.with(itemView.context).load(comics.thumbnail?.getThumb()).into(binding.ivPoster)
                    binding.tvComicName.text = comics.title

                    binding.vgMainItemContainer.setOnClickListener {
                        onItemClicked(this@ViewHolder.adapterPosition)
                    }
                }


    }

}