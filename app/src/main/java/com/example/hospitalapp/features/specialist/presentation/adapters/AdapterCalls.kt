package com.example.hospitalapp.features.specialist.presentation.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.hospital.R
import com.example.hospital.databinding.ItemCallBinding
import com.example.hospitalapp.features.specialist.domain.model.AllCalls
import com.example.hospitalapp.utlis.Const
import java.time.format.DateTimeFormatter

class AdapterCalls() :
    RecyclerView.Adapter<AdapterCalls.Holder>() {
    var list: List<AllCalls>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            ItemCallBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = list?.get(position)
        holder.bind(data!!)
    }

    inner class Holder(private val binding: ItemCallBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(call: AllCalls) {
            binding.name.text = call.patientName
            val date = call.createdAt.format(DateTimeFormatter.ofPattern("dd . MM . yyyy"))
            binding.date.text = date
            if (call.status == Const.PENDING)
                binding.status.setImageResource(R.drawable.ic_pending)
            else
                binding.status.setImageResource(R.drawable.ic_done)
        }
    }
}