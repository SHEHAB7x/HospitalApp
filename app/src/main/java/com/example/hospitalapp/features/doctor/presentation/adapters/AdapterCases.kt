package com.example.hospitalapp.features.doctor.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hospital.databinding.ItemCaseBinding
import com.example.hospitalapp.features.doctor.domain.model.AllCases
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AdapterCases(private val listener: OnClickListener) : ListAdapter<AllCases, AdapterCases.Holder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemCaseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }

    inner class Holder(private val binding: ItemCaseBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(case: AllCases) {
            binding.name.text = case.patientName

            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val date = LocalDate.parse(case.createdAt, formatter)
            val formattedDate = date.format(DateTimeFormatter.ofPattern("dd . MM . yyyy"))
            binding.date.text = formattedDate

            binding.btnShowDetails.setOnClickListener {
                listener.onShowDetailsClicked(case.id)
            }
        }
    }

    interface OnClickListener {
        fun onShowDetailsClicked(id: Int)
    }

    class DiffCallback : DiffUtil.ItemCallback<AllCases>() {
        override fun areItemsTheSame(oldItem: AllCases, newItem: AllCases): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AllCases, newItem: AllCases): Boolean {
            return oldItem == newItem
        }
    }
}
