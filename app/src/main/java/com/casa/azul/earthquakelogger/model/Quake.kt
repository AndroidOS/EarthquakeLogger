package com.casa.azul.earthquakelogger.model

data class Metadata(
    val generated: Long?,
    val url: String?,
    val title: String?,
    val status: Int?,
    val api: String?,
    val count: Int?
)

data class Properties(
    val mag: Double?,
    val place: String?,
    val time: Long?,
    val updated: Long?,
    val tz: Int?,
    val url: String?,
    val detail: String?,
    val felt: String?,
    val cdi: String?,
    val mmi: String?,
    val alert: String?,
    val status: String?,
    val tsunami: Int?,
    val sig: Int?,
    val net: String?,
    val code: String?,
    val ids: String?,
    val sources: String?,
    val types: String?,
    val nst: Int?,
    val dmin: Double?,
    val rms: Double?,
    val gap: Double?,
    val magType: String,
    val type: String?,
    val title: String
)

data class Geometry(
    val type: String?,
    val coordinates: List<Double>
)

data class Feature(
    val type: String?,
    val properties: Properties?,
    val geometry: Geometry?,
    val id: String?
)

data class RootObject(
    val type: String?,
    val metadata: Metadata?,
    val features: List<Feature>?,
    val bbox: List<Double>?
)