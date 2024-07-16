package com.example.hospitalapp.features.specialist.data.mapper

import com.example.hospitalapp.features.specialist.data.model.DataAllCalls
import com.example.hospitalapp.features.specialist.data.model.ModelAllCalls
import com.example.hospitalapp.features.specialist.domain.model.AllCalls

fun ModelAllCalls.toDomain() : List<AllCalls>{
    return this.data.map { it.toDomain() }
}

fun DataAllCalls.toDomain() : AllCalls{
    return AllCalls(
        createdAt = this.created_at,
        id = this.id,
        patientName = this.patient_name,
        status = this.status
    )
}