package com.example.hospitalapp.features.doctor.domain.model

data class AllCallsOfDoctor (
    val createdAt: String,
    val id: Int,
    val patientName: String,
    val status: String
)