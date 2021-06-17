package com.example.finedust.domain.usecase

import com.example.finedust.data.model.airquality.AirQualityResponse
import com.example.finedust.data.util.Resource
import com.example.finedust.domain.repository.AirKoreaRepository

class GetRealtimeAirQualitiesUseCase(
    private val airKoreaRepository: AirKoreaRepository
) {

    suspend fun execute(stationName: String) : Resource<AirQualityResponse> {
        return airKoreaRepository.getRealtimeAirQualities(stationName)
    }
}