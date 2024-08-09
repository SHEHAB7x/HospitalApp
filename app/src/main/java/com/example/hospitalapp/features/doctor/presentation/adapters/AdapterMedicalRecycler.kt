package com.example.hospitalapp.features.doctor.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hospital.databinding.ItemMeasurementBinding

class AdapterMedicalRecycler(private val listener: OnItemClickListener) : RecyclerView.Adapter<AdapterMedicalRecycler.Holder>() {

    var list: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemMeasurementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(list[position], position)
    }

    inner class Holder(private val binding: ItemMeasurementBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(text: String, position: Int) {
            binding.tabTxt.text = text
            binding.btnX.setOnClickListener {
                listener.onCancelClicked(position)
            }
        }
    }

    fun addItem(text: String) {
        list.add(text)
        notifyItemInserted(list.size - 1)
    }

    fun removeItem(position: Int) {
        if (position >= 0 && position < list.size) {
            list.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, list.size)
        }
    }

    interface OnItemClickListener {
        fun onCancelClicked(position: Int)
    }
}
