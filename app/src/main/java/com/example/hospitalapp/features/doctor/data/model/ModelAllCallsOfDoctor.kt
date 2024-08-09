package com.example.hospitalapp.features.doctor.data.model

data class ModelAllCallsOfDoctor(
    val `data`: List<DataAllCallsOfDoctor>,
    val message: String,
    val status: Int
)
data class DataAllCallsOfDoctor(
    val created_at: String,
    val id: Int,
    val patient_name: String,
    val status: String
)