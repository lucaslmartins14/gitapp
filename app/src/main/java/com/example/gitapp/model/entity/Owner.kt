package com.example.gitapp.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

@Entity(
    tableName = "owner"

)
data class Owner (

    @PrimaryKey
    @Expose
    val id: Long,
    @Expose
    val avatar_url: String,
    @Expose
    val login: String

)