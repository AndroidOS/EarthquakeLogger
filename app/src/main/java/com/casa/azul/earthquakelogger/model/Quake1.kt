package com.casa.azul.earthquakelogger.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Quake1(
    val place: String?,
    val mag: Double?
) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}