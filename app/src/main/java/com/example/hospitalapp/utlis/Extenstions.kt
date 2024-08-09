package com.example.hospitalapp.utlis

import android.widget.Toast
import androidx.fragment.app.Fragment
import java.util.regex.Pattern

fun String.isEmailValid(): Boolean {
    val emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,}$"
    val pattern = Pattern.compile(emailPattern)
    return pattern.matcher(this).matches() && !this.contains(" ")
}

fun Fragment.showToast(message: Any) {
    Toast.makeText(requireContext(), "$message", Toast.LENGTH_SHORT).show()
}

fun String.isValidPhoneNumber(): Boolean {
    return this.startsWith("010")
            || this.startsWith("011")
            || this.startsWith("012")
            || this.startsWith("015")
}