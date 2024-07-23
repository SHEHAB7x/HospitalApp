package com.example.hospitalapp.features.receptionist.domain.model

data class AllCalls(
    val createdAt: String,
    val id: Int,
    val patientName: String,
    val status: String
)