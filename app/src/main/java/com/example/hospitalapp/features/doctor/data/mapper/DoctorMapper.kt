package com.example.hospitalapp.features.doctor.data.mapper

import com.example.hospitalapp.features.doctor.data.model.DataAllCallsOfDoctor
import com.example.hospitalapp.features.doctor.data.model.DataAllCases
import com.example.hospitalapp.features.doctor.data.model.ModelAllCallsOfDoctor
import com.example.hospitalapp.features.doctor.data.model.ModelAllCases
import com.example.hospitalapp.features.doctor.data.model.ModelCaseDetails
import com.example.hospitalapp.features.doctor.data.model.ModelLogoutCall
import com.example.hospitalapp.features.doctor.domain.model.AllCallsOfDoctor
import com.example.hospitalapp.features.doctor.domain.model.AllCases
import com.example.hospitalapp.features.doctor.domain.model.CaseDetails
import com.example.hospitalapp.features.doctor.domain.model.LogoutCall


fun ModelAllCallsOfDoctor.toDomain(): List<AllCallsOfDoctor> {
    return this.data.map { it.toDomain() }
}

fun DataAllCallsOfDoctor.toDomain(): AllCallsOfDoctor {
    return AllCallsOfDoctor(
        patientName = this.patient_name,
        createdAt = this.created_at,
        id = this.id,
        status = this.status
    )
}

fun ModelAllCases.toDomain(): List<AllCases> {
    return this.data.map { it.toDomain() }
}

fun DataAllCases.toDomain(): AllCases {
    return AllCases(
        id = this.id,
        patientName = this.patient_name,
        createdAt = this.created_at
    )
}

fun ModelCaseDetails.toDomain(): CaseDetails {
    return CaseDetails(
        age = this.data.age,
        caseStatus = this.data.case_status,
        createdAt = this.data.created_at,
        description = this.data.description,
        doctorId = this.data.doctor_id,
        nurseName = this.data.nurse_id,
        patientName = this.data.patient_name,
        phone = this.data.phone,
        status = this.data.status,
        bloodPressure = this.data.blood_pressure,
        sugarAnalysis = this.data.sugar_analysis,
        analystName = this.data.analysis_id,
        medicalRecordNote = this.data.medical_record_note,
        measurementNote = this.data.measurement_note,
        image = this.data.image
    )
}
fun ModelLogoutCall.toDomain() : LogoutCall {
    return LogoutCall(
        message = this.message
    )
}