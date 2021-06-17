package com.example.finedust.domain.usecase

import com.example.finedust.data.model.tmcoordinates.TmCoordinatesResponse
import com.example.finedust.data.util.Resource
import com.example.finedust.domain.repository.KakaoRepository

class GetTmCoordinatesUseCase(private val kakaoRepository: KakaoRepository) {

    suspend fun execute(longitude: Double, latitude: Double) : Resource<TmCoordinatesResponse> {
        return kakaoRepository.getTmCoordinates(longitude, latitude)
    }
}