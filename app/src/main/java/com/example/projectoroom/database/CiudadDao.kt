package com.example.projectoroom.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CiudadDao {
    @Query("SELECT * FROM tblCiudad")
    fun getAll(): Flow<List<Ciudad>>

    @Insert
    fun insertCiudad(ciudad: Ciudad)

    @Update
    fun updateCiudad(ciudad: Ciudad)

    @Delete
    fun deleteCiudad(ciudad: Ciudad)

    @Query("DELETE FROM tblCiudad WHERE id = :id")
    fun deleteCiudad(id: Int)
}