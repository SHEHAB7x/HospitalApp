package com.example.hospitalapp.features.receptionist.data.model

data class ModelAllDoctors(
    val `data`: List<DataAllDoctors>,
    val message: String,
    val status: Int
)

data class DataAllDoctors(
    val avatar: String,
    val first_name: String,
    val id: Int,
    val type: String
)