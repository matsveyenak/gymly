package com.example.gymly.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "clients")
data class Client(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val fullName: String,
    val phoneNumber: String?,
    val instagramNickname: String?,
    val telegramNickname: String?,
    val whatsappNickname: String?
)