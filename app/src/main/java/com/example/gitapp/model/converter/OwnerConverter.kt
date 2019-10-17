package com.example.gitapp.model.converter

import androidx.room.TypeConverter
import com.example.gitapp.model.entity.Owner
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class OwnerConverter {
    @TypeConverter
    fun fromOwner (owner: Owner?) : String?{
        if (owner == null){
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<Owner>(){

        }.type
        return gson.toJson(owner, type)
    }

    @TypeConverter
    fun toOwner (owner: String?) : Owner?{
        if (owner == null){
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<Owner>(){

        }.type
        return gson.fromJson(owner, type)
    }
}