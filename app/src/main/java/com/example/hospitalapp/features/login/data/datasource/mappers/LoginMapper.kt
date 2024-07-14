package com.example.hospitalapp.features.login.data.datasource.mappers

import com.example.hospitalapp.features.login.data.model.ModelUser
import com.example.hospitalapp.features.login.domain.models.User

object LoginMapper {
    fun mapToDomain(modelUser: ModelUser): User {
        return User(
            accessToken = modelUser.data.access_token,
            address = modelUser.data.address,
            birthday = modelUser.data.birthday,
            createdAt = modelUser.data.created_at,
            email = modelUser.data.email,
            firstName = modelUser.data.first_name,
            gender = modelUser.data.gender,
            id = modelUser.data.id,
            lastName = modelUser.data.last_name,
            mobile = modelUser.data.mobile,
            specialist = modelUser.data.specialist,
            status = modelUser.data.status,
            tokenType = modelUser.data.token_type,
            type = modelUser.data.type,
            verified = modelUser.data.verified)
    }
}

fun ModelUser.toDomain(): User {
    return User(
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
