package com.example.hospitalapp.features.receptionist.data.mapper

import com.example.hospitalapp.features.receptionist.data.model.DataAllCalls
import com.example.hospitalapp.features.receptionist.data.model.DataAllDoctors
import com.example.hospitalapp.features.receptionist.data.model.ModelAllCalls
import com.example.hospitalapp.features.receptionist.data.model.ModelAllDoctors
import com.example.hospitalapp.features.receptionist.data.model.ModelCreateCall
import com.example.hospitalapp.features.receptionist.data.model.ModelLogoutCall
import com.example.hospitalapp.features.receptionist.data.model.ModelShowCall
import com.example.hospitalapp.features.receptionist.domain.model.AllCalls
import com.example.hospitalapp.features.receptionist.domain.model.AllDoctors
import com.example.hospitalapp.features.receptionist.domain.model.CreateCall
import com.example.hospitalapp.features.receptionist.domain.model.LogoutCall
import com.example.hospitalapp.features.receptionist.domain.model.ShowCall

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

fun ModelCreateCall.toDomain() : CreateCall{
    return CreateCall(
        message = this.message
    )
}

fun ModelLogoutCall.toDomain() : LogoutCall {
    return LogoutCall(
        message = this.message
    )
}

fun ModelAllDoctors.toDomain() : List<AllDoctors>{
    return this.data.map { it.toDomain() }
}

fun DataAllDoctors.toDomain() : AllDoctors{
    return AllDoctors(
        avatar = this.avatar,
        first_name = this.first_name,
        id = this.id,
        type = this.type
    )
}

fun ModelShowCall.toDomain() : ShowCall {
    return ShowCall(
        id = this.data.id,
        patientName = this.data.patient_name,
        createdAt = this.data.created_at,
        status = this.data.status,
        caseStatus = this.data.case_status,
        age = this.data.age,
        phone = this.data.phone,
        description = this.data.description
    )
}