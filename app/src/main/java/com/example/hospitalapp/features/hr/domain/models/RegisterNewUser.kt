package com.example.hospitalapp.features.hr.domain.models

import com.example.hospitalapp.features.hr.data.models.RegisterData

data class RegisterNewUser(
    val `data`: RegisterData,
    val message: String,
    val status: Int
)