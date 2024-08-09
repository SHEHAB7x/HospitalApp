package com.example.hospitalapp.features.doctor.presentation.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.hospitalapp.features.doctor.presentation.view.DoctorCaseDetailsFragment
import com.example.hospitalapp.features.doctor.presentation.view.MedicalMeasurementDetailsFragment
import com.example.hospitalapp.features.doctor.presentation.view.MedicalRecordDetailsFragment
import com.example.hospitalapp.utlis.Const

class AdapterViewPager(
    fragmentActivity: FragmentActivity,
    private val caseId: Int
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = when (position) {
            0 -> DoctorCaseDetailsFragment()
            1 -> MedicalRecordDetailsFragment()
            2 -> MedicalMeasurementDetailsFragment()
            else -> throw IllegalStateException("Unexpected position $position")
        }
        fragment.arguments = Bundle().apply {
            putInt(Const.CASE_ID, caseId)
        }
        return fragment
    }
}

