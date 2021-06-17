package com.example.finedust.domain.repository

import com.example.finedust.data.model.airquality.AirQualityResponse
import com.example.finedust.data.model.monitoringstation.MonitoringStationsResponse
import com.example.finedust.data.util.Resource

interface AirKoreaRepository {

    suspend fun getNearbyMonitoringStation(tmX: Double, tmY: Double): Resource<MonitoringStationsResponse>

    suspend fun getRealtimeAirQualities(stationName: String) : Resource<AirQualityResponse>
}