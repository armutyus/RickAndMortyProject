package com.armutyus.rickandmortyproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.armutyus.rickandmortyproject.R
import com.armutyus.rickandmortyproject.model.Result
import com.armutyus.rickandmortyproject.view.CharactersFragmentDirections
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class CharactersAdapter @Inject constructor(
    private val glide: RequestManager
) : RecyclerView.Adapter<CharactersAdapter.CharactersViewHolder>() {

    class CharactersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val diffUtil = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }


    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var characters = ArrayList<Result>()
    var charImage: List<String>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)
    var charName: List<String>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.characters_row, parent, false)
        return CharactersViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {

        val imageView = holder.itemView.findViewById<ImageView>(R.id.charactersImage)
        val charNameText = holder.itemView.findViewById<TextView>(R.id.charactersName)
        val charsImage = charImage[position]
        val charName = charName[position]
        holder.itemView.apply {
            charNameText.text = charName
            glide.load(charsImage).fitCenter().into(imageView)
        }

        holder.itemView.setOnClickListener {
            val action =
                CharactersFragmentDirections.actionCharactersFragmentToCharacterDetailsFragment(
                    characters[position].id
                )
            Navigation.findNavController(it).navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return charImage.size
    }
}