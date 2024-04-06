package com.example.lab2.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lab2.Model.entity.Dog
import com.example.lab2.R

class DogAdapter(private var dogList: List<Dog>) :
    RecyclerView.Adapter<DogAdapter.DogViewHolder>() {

    inner class DogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewName: TextView = itemView.findViewById(R.id.textViewName)
        private val textViewBarking: TextView = itemView.findViewById(R.id.textViewBarking)
        private val textViewProtectiveness: TextView = itemView.findViewById(R.id.textViewProtectiveness)
        private val textViewEnergy: TextView = itemView.findViewById(R.id.textViewEnergy)
        private val imageViewLink: ImageView = itemView.findViewById(R.id.imageViewLink)

        fun bind(dog: Dog) {
            Glide.with(itemView.context)
                .load(dog.image_link)
                .placeholder(R.drawable.download) // Заглушка, если изображение не загружено
                .into(imageViewLink)
            textViewName.text = dog.name
            textViewEnergy.text = dog.energy.toString()
            textViewBarking.text = "Barking: ${dog.barking}"
            textViewProtectiveness.text = "Protectiveness: ${dog.protectiveness}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_dog, parent, false)
        return DogViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        val currentItem = dogList[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int {
        return dogList.size
    }

    fun updateList(newList: List<Dog>) {
        dogList = newList
        notifyDataSetChanged()
    }
}
