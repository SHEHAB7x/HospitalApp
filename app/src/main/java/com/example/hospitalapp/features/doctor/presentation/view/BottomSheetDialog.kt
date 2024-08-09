package com.example.hospitalapp.features.doctor.presentation.view

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.hospital.R
import com.example.hospital.databinding.FragmentBottomSheetDialogBinding
import com.example.hospitalapp.utlis.Const
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetDialog : BottomSheetDialogFragment() {

    private var _binding: FragmentBottomSheetDialogBinding? = null
    private val binding get() = _binding!!
    private var medicalType: String? = null

    var onDismissListener: (() -> Unit)? = null
    private var onMedicalTypeSelectedListener: OnMedicalTypeSelectedListener? = null

    interface OnMedicalTypeSelectedListener {
        fun onMedicalTypeSelected(medicalType: String)
    }

    fun setOnMedicalTypeSelectedListener(listener: OnMedicalTypeSelectedListener) {
        onMedicalTypeSelectedListener = listener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBottomSheetDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDialogHeight()
        onClicks()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        onDismissListener?.invoke()
    }

    private fun setDialogHeight() {
        dialog?.setOnShowListener { dialogInterface ->
            val bottomSheetDialog = dialogInterface as BottomSheetDialog
            val bottomSheet = bottomSheetDialog.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
            bottomSheet?.let {
                val behavior = BottomSheetBehavior.from(it)
                val layoutParams = it.layoutParams
                layoutParams.height = 270.dpToPx()
                it.layoutParams = layoutParams
                behavior.peekHeight = 270.dpToPx()
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
    }

    private fun onClicks() {
        binding.btnMedicalRecord.setOnClickListener {
            medicalType = Const.RECORD
            updateUI(
                selectedButton = binding.btnMedicalRecord,
                selectedIcon = binding.icMedicalRecord,
                selectedText = binding.txtMedicalRecord,
                deselectedButton = binding.btnMedicalMeasurement,
                deselectedIcon = binding.icMedicalMeasurement,
                deselectedText = binding.txtMedicalMeasurement,
                selectedIconRes = R.drawable.ic_medical_record_selected,
                deselectedIconRes = R.drawable.ic_medical_measurement
            )
        }

        binding.btnMedicalMeasurement.setOnClickListener {
            medicalType = Const.MEASUREMENT
            updateUI(
                selectedButton = binding.btnMedicalMeasurement,
                selectedIcon = binding.icMedicalMeasurement,
                selectedText = binding.txtMedicalMeasurement,
                deselectedButton = binding.btnMedicalRecord,
                deselectedIcon = binding.icMedicalRecord,
                deselectedText = binding.txtMedicalRecord,
                selectedIconRes = R.drawable.ic_medical_measurement_selected,
                deselectedIconRes = R.drawable.ic_medical_record
            )
        }

        binding.btnRequest33.setOnClickListener {
            onMedicalTypeSelectedListener?.onMedicalTypeSelected(medicalType ?: "")
            dismiss()
        }
    }

    private fun updateUI(
        selectedButton: View,
        selectedIcon: ImageView,
        selectedText: TextView,
        deselectedButton: View,
        deselectedIcon: ImageView,
        deselectedText: TextView,
        selectedIconRes: Int,
        deselectedIconRes: Int
    ) {
        selectedIcon.setImageResource(selectedIconRes)
        selectedText.setTextColor(ContextCompat.getColor(requireContext(), R.color.mint_green))
        selectedButton.setBackgroundResource(R.drawable.frame_sheet_green)

        deselectedIcon.setImageResource(deselectedIconRes)
        deselectedText.setTextColor(ContextCompat.getColor(requireContext(), R.color.dialog_gray))
        deselectedButton.setBackgroundResource(R.drawable.frame_sheet_gray)
    }

    private fun Int.dpToPx(): Int {
        return (this * resources.displayMetrics.density).toInt()
    }
}
