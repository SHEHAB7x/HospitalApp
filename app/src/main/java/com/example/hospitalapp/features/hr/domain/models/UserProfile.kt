package com.example.hospitalapp.features.hr.domain.models

data class UserProfile(
    val accessToken: String,
    val address: String,
    val birthday: String,
    val createdAt: String,
    val email: String,
    val firstName: String,
    val gender: String,
    val id: Int,
    val lastName: String,
    val mobile: String,
    val specialist: String,
    val status: String,
    val tokenType: String,
    val type: String,
    val verified: Boolean
)