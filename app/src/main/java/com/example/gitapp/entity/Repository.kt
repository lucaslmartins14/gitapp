package com.example.gitapp.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

@Entity(
    tableName = "repository"

)
data class Repository (

    @PrimaryKey
    @Expose
    val id: Long,
    @Expose
    val name: String

)





