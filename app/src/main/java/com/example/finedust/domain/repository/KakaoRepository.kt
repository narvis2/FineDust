package com.example.finedust.domain.repository

import com.example.finedust.data.model.tmcoordinates.TmCoordinatesResponse
import com.example.finedust.data.util.Resource


interface KakaoRepository {

    suspend fun getTmCoordinates(longitude: Double, latitude: Double) : Resource<TmCoordinatesResponse>
}