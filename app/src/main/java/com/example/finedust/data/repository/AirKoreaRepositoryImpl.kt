package com.example.finedust.data.repository

import com.example.finedust.data.mapper.airQualityResponseToResource
import com.example.finedust.data.mapper.monitoringStationsResponseToResource
import com.example.finedust.data.mapper.responseToResource
import com.example.finedust.data.model.airquality.AirQualityResponse
import com.example.finedust.data.model.monitoringstation.MonitoringStationsResponse
import com.example.finedust.data.repository.datasource.AirKoreaDataSource
import com.example.finedust.data.util.Resource
import com.example.finedust.domain.repository.AirKoreaRepository

class AirKoreaRepositoryImpl(
    private val airKoreaDataSource: AirKoreaDataSource
) : AirKoreaRepository {
    override suspend fun getNearbyMonitoringStation(
        tmX: Double,
        tmY: Double
    ): Resource<MonitoringStationsResponse> {
        return responseToResource(airKoreaDataSource.getNearbyMonitoringStation(tmX, tmY))
    }

    override suspend fun getRealtimeAirQualities(stationName: String): Resource<AirQualityResponse> {
        return responseToResource(airKoreaDataSource.getRealtimeAirQualities(stationName))
    }
}