package com.example.finedust.data.repository.datasource

import com.example.finedust.data.model.airquality.AirQualityResponse
import com.example.finedust.data.model.monitoringstation.MonitoringStationsResponse
import retrofit2.Response
import retrofit2.http.Query

interface AirKoreaDataSource {

    suspend fun getNearbyMonitoringStation(tmX: Double, tmY: Double) : Response<MonitoringStationsResponse>

    suspend fun getRealtimeAirQualities(stationName: String) : Response<AirQualityResponse>
}