package com.example.hospitalapp.features.doctor.domain.model

data class CaseDetails(
    val age: String,
    val caseStatus: String,
    val createdAt: String,
    val description: String,
    val doctorId: String,
    val nurseName: String,
    val patientName: String,
    val phone: String,
    val status: String,
    val bloodPressure: String,
    val sugarAnalysis: String,
    val analystName: String,
    val measurementNote: String,
    val medicalRecordNote: String,
    val image: String
)