package com.example.gitapp.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

@Entity(
    tableName = "gitrepository"

)
data class GR (

    @PrimaryKey
    @Expose
    val id: Long,
    @Expose
    val name:
    String
)





