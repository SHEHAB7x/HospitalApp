package com.example.hospitalapp.features.specialist.domain.model

data class ShowCall (
    val id: Int,
    val patientName: String,
    val createdAt: String,
    val status: String,
    val caseStatus: String,
    val age: String,
    val phone: String,
    val description: String
)