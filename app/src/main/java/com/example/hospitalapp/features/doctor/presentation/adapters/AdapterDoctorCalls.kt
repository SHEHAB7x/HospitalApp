package com.example.hospitalapp.features.doctor.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hospital.databinding.ItemCallDoctorBinding
import com.example.hospitalapp.features.doctor.domain.model.AllCallsOfDoctor
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AdapterDoctorCalls(private val listener: OnItemClick) :
    ListAdapter<AllCallsOfDoctor, AdapterDoctorCalls.Holder>(DoctorCallsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemCallDoctorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class Holder(private val binding: ItemCallDoctorBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(call: AllCallsOfDoctor) {
            binding.name.text = call.patientName
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val date = LocalDate.parse(call.createdAt, formatter)
            val formattedDate = date.format(DateTimeFormatter.ofPattern("dd . MM . yyyy"))
            binding.date.text = formattedDate

            binding.btnAccept.setOnClickListener {
                listener.onAcceptClicked(call.id)
            }
            binding.btnReject.setOnClickListener {
                listener.onRejectClicked(call.id)
            }
        }
    }

    interface OnItemClick {
        fun onAcceptClicked(id: Int)
        fun onRejectClicked(id: Int)
    }
}

class DoctorCallsDiffCallback : DiffUtil.ItemCallback<AllCallsOfDoctor>() {
    override fun areItemsTheSame(oldItem: AllCallsOfDoctor, newItem: AllCallsOfDoctor): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AllCallsOfDoctor, newItem: AllCallsOfDoctor): Boolean {
        return oldItem == newItem
    }
}
