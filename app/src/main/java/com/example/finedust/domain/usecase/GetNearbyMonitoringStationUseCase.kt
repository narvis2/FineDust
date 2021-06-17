package com.example.finedust.domain.usecase

import com.example.finedust.data.model.monitoringstation.MonitoringStationsResponse
import com.example.finedust.data.util.Resource
import com.example.finedust.domain.repository.AirKoreaRepository

class GetNearbyMonitoringStationUseCase(private val airKoreaRepository: AirKoreaRepository) {

    suspend fun execute(tmX: Double, tmY: Double) : Resource<MonitoringStationsResponse> {
        return airKoreaRepository.getNearbyMonitoringStation(tmX, tmY)
    }
}