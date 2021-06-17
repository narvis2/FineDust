package com.example.finedust.data.repository.datasourceimpl

import com.example.finedust.data.api.AirKoreaApiService
import com.example.finedust.data.model.airquality.AirQualityResponse
import com.example.finedust.data.model.monitoringstation.MonitoringStationsResponse
import com.example.finedust.data.repository.datasource.AirKoreaDataSource
import retrofit2.Response

class AirKoreaDataSourceImpl(
    private val airKoreaApiService: AirKoreaApiService
) : AirKoreaDataSource {
    override suspend fun getNearbyMonitoringStation(
        tmX: Double,
        tmY: Double
    ): Response<MonitoringStationsResponse> {
        return airKoreaApiService.getNearbyMonitoringStation(tmX, tmY)
    }

    override suspend fun getRealtimeAirQualities(stationName: String): Response<AirQualityResponse> {
        return airKoreaApiService.getRealtimeAirQualities(stationName)
    }
}