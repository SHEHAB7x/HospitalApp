package com.example.hospitalapp.features.receptionist.data.model

data class ModelAllCalls(
    val `data`: List<DataAllCalls>,
    val message: String,
    val status: Int
)

data class DataAllCalls(
    val created_at: String,
    val id: Int,
    val patient_name: String,
    val status: String
)