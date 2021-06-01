package com.example.homework11

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework11.databinding.ViewHolderLayoutBinding
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

class RecyclerAdapter():RecyclerView.Adapter<RecyclerAdapter.viewHolder>() {
    private val items = mutableListOf<Model>()

    inner class viewHolder(private val binding : ViewHolderLayoutBinding ) : RecyclerView.ViewHolder(binding.root){
        private lateinit var model: Model
        fun bind(){
            model = items[adapterPosition]
            binding.imageV
            binding.txVCapital.text = model.capital
            binding.txVName.text= model.name
            binding.txVRegion.text = model.region

            model.flag?.let { getImage(it) }
        }
        fun getImage(url : String){
            GlideToVectorYou.init().with(binding.root.context).load(Uri.parse(url),binding.imageV)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        return  viewHolder(ViewHolderLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {

        holder.bind()

    }

    override fun getItemCount()= items.size

    fun setData(items : MutableList<Model>){
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun clearData (){
        this.items.clear()
        notifyDataSetChanged()
    }


}