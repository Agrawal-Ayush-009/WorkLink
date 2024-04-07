package com.example.worklink.adapters

import android.content.Context
import android.graphics.PorterDuff
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.worklink.R
import com.example.worklink.databinding.FactoryGigBinding
import com.example.worklink.databinding.FragmentAllBinding
import com.example.worklink.models.Gig
import com.example.worklink.models.Location

class ManufacturerGigRVAdapter(val context: Context, val list: List<Gig>) :
    RecyclerView.Adapter<ManufacturerGigRVAdapter.ManufacturerGigViewholder>() {
    private val likedMap = mutableMapOf<Int, Boolean>()

//    private var mListener: OnItemClickListener? = null

    inner class ManufacturerGigViewholder(val binding: FactoryGigBinding) :
        RecyclerView.ViewHolder(binding.root)
//    interface OnItemClickListener {
//        fun onItemClick(view: View, position: Int)
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManufacturerGigViewholder {
        val binding = FactoryGigBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ManufacturerGigViewholder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ManufacturerGigViewholder, position: Int) {
        val name = list[position].companyName
        val location = list[position].location
        val pay = list[position].pay
        val skillsRequired = list[position].skillsRequired
        val workerLimit = list[position].workerLimit
        val description = list[position].description

        holder.binding.factoryName.setText(name).toString()
        holder.binding.address.setText(buildString {
            append(location.city)
            append(", ")
            append(location.state)
        }).toString()
        var skills: String = ""
        for(i in skillsRequired){
            skills += "$i, "
        }
        holder.binding.skillRequired.setText(skills).toString()
        holder.binding.pay.setText("$pay/month")
        holder.binding.limit.text = "Vacancy: $workerLimit"
        holder.binding.likeButton.isVisible = false

//        holder.itemView.setOnClickListener {
//            holder.binding.likeButton.setImageResource(R.drawable.like_logo_filled)
//        }
//        holder.itemView.setOnClickListener {
//            Log.e("id123","$likedMap")
//            val currentState = likedMap[position] ?: false
//            likedMap[position] = !currentState
//            if (likedMap[position] == true) {
//                holder.binding.likeButton.setImageResource(R.drawable.like_logo_filled)
//            } else {
//                holder.binding.likeButton.setImageResource(R.drawable.like_logo)
//            }
//        }
    }
}