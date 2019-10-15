package com.example.gitapp.model

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.gitapp.model.entity.GR

@Dao
interface GrDAO {
    @get:Query("SELECT * from gitrepository")
    val allGrs: LiveData<List<GR>>

    @get:Query("SELECT * from gitrepository")
    val listGrs: List<GR>

    @Query("SELECT * from gitrepository")
    fun getGrs(): LiveData<List<GR>>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    @JvmSuppressWildcards
    fun insert(gr: GR)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    @JvmSuppressWildcards
    fun insertAll(objects: List<GR>)

    @Query("DELETE FROM gitrepository")
    fun deleteAll()

    @Delete
    fun deleteItem(gr: GR)

    @Update
    fun updateItem(gr:GR)
}