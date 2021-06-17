package com.example.finedust.data.repository

import com.example.finedust.data.mapper.responseToResource
import com.example.finedust.data.model.tmcoordinates.TmCoordinatesResponse
import com.example.finedust.data.repository.datasource.KakaoDataSource
import com.example.finedust.data.util.Resource
import com.example.finedust.domain.repository.KakaoRepository

class KakaoRepositoryImpl(
    private val kakaoDataSource: KakaoDataSource
) : KakaoRepository {

    override suspend fun getTmCoordinates(
        longitude: Double,
        latitude: Double
    ): Resource<TmCoordinatesResponse> {
        return responseToResource(kakaoDataSource.getTmCoordinates(longitude, latitude))
    }
}