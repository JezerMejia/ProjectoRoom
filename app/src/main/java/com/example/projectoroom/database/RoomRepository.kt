package com.example.projectoroom.database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class RoomRepository private constructor(private val dao: CiudadDao) {

    val ciudades: Flow<List<Ciudad>> = dao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(ciudad: Ciudad) {
        dao.insertCiudad(ciudad)
    }

    companion object {
        private var INSTANCE: RoomRepository? = null

        fun init(dao: CiudadDao): RoomRepository {
            if (INSTANCE != null) return INSTANCE!!
            INSTANCE = RoomRepository(dao)
            return INSTANCE!!
        }

        fun getInstance(): RoomRepository {
            return INSTANCE!!
        }
    }
}