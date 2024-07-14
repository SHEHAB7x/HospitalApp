package com.example.hospitalapp.features.hr.data.mapper

import com.example.hospitalapp.features.hr.data.models.DataAllUsers
import com.example.hospitalapp.features.hr.data.models.ModelAllUsers
import com.example.hospitalapp.features.hr.data.models.ModelProfileUser
import com.example.hospitalapp.features.hr.data.models.ModelRegisterNewUser
import com.example.hospitalapp.features.hr.domain.models.RegisterNewUser
import com.example.hospitalapp.features.hr.domain.models.User
import com.example.hospitalapp.features.hr.domain.models.UserProfile


fun ModelAllUsers.toDomain(): List<User> {
    return this.data.map { it.toDomain() }
}

fun DataAllUsers.toDomain(): User {
    return User(
        avatar = this.avatar,
        firstName = this.first_name,
        id = this.id,
        type = this.type
    )
}

fun ModelProfileUser.toDomain() : UserProfile {
    return UserProfile(
        accessToken = this.data.access_token,
        address = this.data.address,
        birthday = this.data.birthday,
        createdAt = this.data.created_at,
        email = this.data.email,
        firstName = this.data.first_name,
        gender = this.data.gender,
        id = this.data.id,
        lastName = this.data.last_name,
        mobile = this.data.mobile,
        specialist = this.data.specialist,
        status = this.data.status,
        tokenType = this.data.token_type,
        type = this.data.type,
        verified = this.data.verified
    )
}

fun ModelRegisterNewUser.toDomain() : RegisterNewUser {
    return RegisterNewUser(
        data = this.data,
        message = this.message,
        status = this.status
    )
}
