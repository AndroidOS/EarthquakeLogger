package com.casa.azul.earthquakelogger.model

import androidx.room.Dao
import androidx.room.Insert


@Dao
interface QuakeDao {

    @Insert
    suspend fun insertAll(vararg quakes: Quake1): List<Long>

    @androidx.room.Query("SELECT * FROM quake1")
    suspend fun getAllQuakes(): List<Quake1>

    @androidx.room.Query("SELECT * FROM quake1 WHERE uuid = :quakeId")
    suspend fun getQuake(quakeId: Int): Quake1

    @androidx.room.Query("DELETE FROM quake1")
    suspend fun deleteAllQuakes()
}