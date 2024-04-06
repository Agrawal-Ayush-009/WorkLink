package com.example.worklink.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.worklink.databinding.FactoryGigBinding
import com.example.worklink.databinding.FragmentAllBinding
import com.example.worklink.models.Gig
import com.example.worklink.models.Location

class WorkerGigRVAdapter(val context: Context, val list: List<Gig>) :
    RecyclerView.Adapter<WorkerGigRVAdapter.WorkerGigViewHolder>() {
    inner class WorkerGigViewHolder(val binding: FactoryGigBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkerGigViewHolder {
        val binding = FactoryGigBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WorkerGigViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: WorkerGigViewHolder, position: Int) {
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
        var skills: String = "Skills Required: \n"
        for(i in skillsRequired){
            skills += "$i, "
        }
        holder.binding.skillRequired.setText(skills).toString()
        holder.binding.pay.setText("$pay/month")
        holder.binding.limit.text = "Vacancy: $workerLimit"
    }
}