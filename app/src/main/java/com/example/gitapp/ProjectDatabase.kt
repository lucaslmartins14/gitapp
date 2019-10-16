package com.example.gitapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.gitapp.model.GrDAO
import com.example.gitapp.model.entity.GR

@Database(
    entities = [GR::class],
    version = 1,
    exportSchema = false
)
abstract class ProjectDatabase : RoomDatabase() {
    abstract fun grDao(): GrDAO

    companion object {

        @Volatile
        private var INSTANCE: ProjectDatabase? = null

        internal fun getDatabase(context: Context): ProjectDatabase? {
            if (INSTANCE == null) {
                synchronized(ProjectDatabase::class) {
                    INSTANCE =
                        Room.databaseBuilder(
                            context.applicationContext,
                            ProjectDatabase::class.java,
                            "project_database"
                        )
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build()
                }

            }
            return INSTANCE
        }
    }

    fun destroyDataBase() {
        INSTANCE = null
    }

}