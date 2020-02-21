package com.casa.azul.earthquakelogger.model

import androidx.room.Dao
import androidx.room.Insert


@Dao
interface QuakeDao {

    @Insert
    suspend fun insertAll(vararg quakes: Quake1): List<Long>

    @androidx.room.Query("SELECT * FROM quakes")
    suspend fun getAllDogs(): List<Quake1>

    @androidx.room.Query("SELECT * FROM quakes WHERE uuid = :dogId")
    suspend fun getDog(dogId: Int): Quake1

    @androidx.room.Query("DELETE FROM q")
    suspend fun deleteAllDogs()
}