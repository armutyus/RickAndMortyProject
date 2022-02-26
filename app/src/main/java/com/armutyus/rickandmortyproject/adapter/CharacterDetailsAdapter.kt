package com.armutyus.rickandmortyproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.armutyus.rickandmortyproject.R
import com.armutyus.rickandmortyproject.model.Result
import com.bumptech.glide.RequestManager
import javax.inject.Inject

class CharacterDetailsAdapter @Inject constructor(
    private val glide: RequestManager
) : RecyclerView.Adapter<CharacterDetailsAdapter.CharacterDetailsViewHolder>() {

    class CharacterDetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    var characterDetails: ArrayList<String>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterDetailsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.character_details, parent, false)
        return CharacterDetailsViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterDetailsViewHolder, position: Int) {
        val imageView = holder.itemView.findViewById<ImageView>(R.id.detailImage)
        val charNameText = holder.itemView.findViewById<TextView>(R.id.charNameDetail)
        val charStatusText = holder.itemView.findViewById<TextView>(R.id.charStatusDetail)
        val charLocationText = holder.itemView.findViewById<TextView>(R.id.charLocationDetail)
        holder.itemView.apply {
            charNameText.text = characterDetails?.get(3)
            charStatusText.text = characterDetails?.get(1)
            charLocationText.text = characterDetails?.get(2)
            glide.load(characterDetails?.get(0)).into(imageView)
        }
    }

    override fun getItemCount(): Int {
        return 1
    }

}