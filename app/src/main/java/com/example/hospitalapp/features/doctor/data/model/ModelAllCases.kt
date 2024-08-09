package com.example.hospitalapp.features.doctor.data.model

data class ModelAllCases(
    val `data`: List<DataAllCases>,
    val message: String,
    val status: Int
)

data class DataAllCases(
    val created_at: String,
    val id: Int,
    val patient_name: String
)