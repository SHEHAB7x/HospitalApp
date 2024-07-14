package com.example.hospitalapp.features.hr.data.models

data class ModelAllUsers(
    val `data`: List<DataAllUsers>,
    val message: String,
    val status: Int
)
data class DataAllUsers(
    val avatar: String,
    val first_name: String,
    val id: Int,
    val type: String
)