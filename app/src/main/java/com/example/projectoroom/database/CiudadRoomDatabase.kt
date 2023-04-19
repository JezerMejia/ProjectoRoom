package com.example.projectoroom.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Ciudad::class), version = 1, exportSchema = false)
public abstract class CiudadRoomDatabase : RoomDatabase() {

    abstract fun ciudadDao(): CiudadDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: CiudadRoomDatabase? = null

        fun getDatabase(context: Context): CiudadRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CiudadRoomDatabase::class.java,
                    "RoomTest"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
