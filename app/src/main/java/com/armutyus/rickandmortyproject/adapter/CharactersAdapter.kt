package com.armutyus.rickandmortyproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.armutyus.rickandmortyproject.R
import com.armutyus.rickandmortyproject.model.Result
import com.armutyus.rickandmortyproject.view.CharactersFragmentDirections
import com.squareup.picasso.Picasso
import javax.inject.Inject

class CharactersAdapter @Inject constructor(
    private val picasso: Picasso
) : RecyclerView.Adapter<CharactersAdapter.CharactersViewHolder>() {

    class CharactersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    var characters = ArrayList<Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.characters_row, parent, false)
        return CharactersViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {

        val imageView = holder.itemView.findViewById<ImageView>(R.id.charactersImage)
        val charNameText = holder.itemView.findViewById<TextView>(R.id.charactersName)
        val chars = characters[position]
        holder.itemView.apply {
            charNameText.text = chars.name
            picasso.load(chars.image).into(imageView)
        }

        holder.itemView.setOnClickListener {
            val action =
                CharactersFragmentDirections.actionCharactersFragmentToCharacterDetailsFragment(
                    chars.id
                )
            Navigation.findNavController(it).navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return characters.size
    }
}