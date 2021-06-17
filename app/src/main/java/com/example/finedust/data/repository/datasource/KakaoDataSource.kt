package com.example.finedust.data.repository.datasource

import com.example.finedust.data.model.tmcoordinates.TmCoordinatesResponse
import retrofit2.Response
import retrofit2.http.Query

interface KakaoDataSource {

    suspend fun getTmCoordinates(longitude: Double, latitude: Double) : Response<TmCoordinatesResponse>
}