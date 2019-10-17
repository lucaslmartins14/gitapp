package com.example.gitapp.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.gitapp.model.converter.OwnerConverter
import com.google.gson.annotations.Expose

@Entity(
    tableName = "gitrepository"

)
data class GR (
    @PrimaryKey
    @Expose
    val id: Long,
    @Expose
    val name: String,
    @Expose
    val stargazers_count: Long,
    @Expose
    val forks_count: Long,
    @Expose
    @TypeConverters(OwnerConverter::class)
    val owner: Owner

)





