package com.example.finedust.data.model.monitoringstation


import com.google.gson.annotations.SerializedName

data class Station(
    @SerializedName("addr")
    val addr: String?,
    @SerializedName("stationName")
    val stationName: String?,
    @SerializedName("tm")
    val tm: Double?
)