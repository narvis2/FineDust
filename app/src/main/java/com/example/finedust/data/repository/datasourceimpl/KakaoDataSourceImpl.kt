package com.example.finedust.data.repository.datasourceimpl

import com.example.finedust.data.api.KakaoLocalApiService
import com.example.finedust.data.mapper.responseToResource
import com.example.finedust.data.model.tmcoordinates.TmCoordinatesResponse
import com.example.finedust.data.repository.datasource.KakaoDataSource
import retrofit2.Response

class KakaoDataSourceImpl(
    private val kakaoLocalApiService: KakaoLocalApiService
) : KakaoDataSource {
    override suspend fun getTmCoordinates(
        longitude: Double,
        latitude: Double
    ): Response<TmCoordinatesResponse> {
        return kakaoLocalApiService.getTmCoordinates(longitude, latitude)
    }
}