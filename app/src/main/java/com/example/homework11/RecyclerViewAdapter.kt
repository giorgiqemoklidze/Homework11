package com.example.homework11

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework11.databinding.RecyclerLayoutBinding

 class RecyclerViewAdapter(private val items : List<Model>?) : RecyclerView.Adapter<RecyclerViewAdapter.viewHolder>() {

    inner class viewHolder(private val binding: RecyclerLayoutBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(){
            binding.txVName.text = items!![adapterPosition].name.toString()
            binding.txVCapital.text = items[adapterPosition].capital.toString()
            binding.txVRegion.text = items[adapterPosition].region.toString()

        }
    }

     override fun getItemCount() = items!!.size

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
         return  viewHolder(RecyclerLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
     }

     override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.bind()
     }

 }