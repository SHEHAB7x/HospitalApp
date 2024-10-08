package com.example.hospitalapp.features.receptionist.presentation.adapters

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.hospital.R
import com.example.hospital.databinding.ItemCallBinding
import com.example.hospitalapp.features.receptionist.domain.model.AllCalls
import com.example.hospitalapp.utlis.Const
import java.time.LocalDate
import java.time.format.DateTimeFormatter

open class AdapterCalls() :
    RecyclerView.Adapter<AdapterCalls.Holder>() {
    var list: List<AllCalls>? = null
    var listener : OnItemClickListener ?= null
    var rowIndex = -1

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
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val date = LocalDate.parse(call.createdAt, formatter)
            val formattedDate = date.format(DateTimeFormatter.ofPattern("dd . MM . yyyy"))

            binding.date.text = formattedDate
            if (call.status == Const.LOGOUT)
                binding.status.setImageResource(R.drawable.ic_done)
            else
                binding.status.setImageResource(R.drawable.ic_pending)
        }

        init {
            itemView.setOnClickListener{
                rowIndex = layoutPosition
                listener?.onItemClicked(list?.get(layoutPosition)?.id!!)
                notifyDataSetChanged()
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClicked(id : Int)
    }
}