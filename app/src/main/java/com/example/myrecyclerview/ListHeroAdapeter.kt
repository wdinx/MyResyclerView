package com.example.myrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


// Adapter yang akan memformat bagaimana tiap elemen akan ditampilkan
class ListHeroAdapeter(private val listHero: ArrayList<Hero>): RecyclerView.Adapter<ListHeroAdapeter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.imgHerooes)
        val tvItemName: TextView = itemView.findViewById(R.id.tvItemName)
        val tvItemDescription: TextView = itemView.findViewById(R.id.tvItemDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_hero, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listHero.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, photo) = listHero[position]
        holder.imgPhoto.setImageResource(photo)
        holder.tvItemDescription.text = description
        holder.tvItemName.text = name

        holder.itemView.setOnClickListener{onItemClickCallback.onItemClickCallback(listHero[holder.adapterPosition])}

        // Trigger dari adapter
//        holder.itemView.setOnClickListener {
//            Toast.makeText(holder.itemView.context, "Kamu Memilih ${listHero[holder.adapterPosition].name}", Toast.LENGTH_SHORT).show()
//        }
    }

    interface OnItemClickCallback{
        fun onItemClickCallback(data: Hero)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }
}