package com.example.hospitalapp.features.specialist.domain.model

data class AllCalls(
    val createdAt: String,
    val id: Int,
    val patientName: String,
    val status: String
)