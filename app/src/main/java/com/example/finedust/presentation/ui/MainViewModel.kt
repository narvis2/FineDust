package com.example.finedust.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finedust.data.model.airquality.AirQuality
import com.example.finedust.data.model.monitoringstation.Station
import com.example.finedust.data.model.tmcoordinates.Document
import com.example.finedust.data.model.tmcoordinates.TmCoordinatesResponse
import com.example.finedust.data.util.Resource
import com.example.finedust.domain.usecase.GetNearbyMonitoringStationUseCase
import com.example.finedust.domain.usecase.GetRealtimeAirQualitiesUseCase
import com.example.finedust.domain.usecase.GetTmCoordinatesUseCase
import com.example.finedust.presentation.util.NetworkManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.getScopeName
import retrofit2.Response

class MainViewModel(
    private val networkManager: NetworkManager,
    private val getNearbyMonitoringStationUseCase: GetNearbyMonitoringStationUseCase,
    private val getTmCoordinatesUseCase: GetTmCoordinatesUseCase,
    private val getRealtimeAirQualitiesUseCase: GetRealtimeAirQualitiesUseCase
) : ViewModel() {

    private val _airQuality = MutableLiveData<AirQuality>()
    val airQuality : LiveData<AirQuality>
        get() = _airQuality

    private val _station = MutableLiveData<Station>()
    val station : LiveData<Station>
        get() = _station

    fun getMonitoringStation(longitude: Double, latitude: Double) = viewModelScope.launch(Dispatchers.IO) {
        try {
            if (checkNetworkState()) {
                val response = getTmCoordinatesUseCase.execute(longitude, latitude)
                val result = response.data?.documents?.firstOrNull()
                val station = getNearbyStation(result)
                _station.postValue(station!!)
                val stationName = station.stationName
                val quality = stationName?.let {
                    getLatestAirQualityData(it)
                }
                quality?.let {
                    _airQuality.postValue(it)
                }
            } else {

            }
        } catch (e: Exception) {
            error(e.message.toString())
        }
    }

    private suspend fun getNearbyStation(document: Document?) : Station? {
        val tmX = document?.x
        val tmY = document?.y
        val response = getNearbyMonitoringStationUseCase.execute(tmX!!, tmY!!)
        return response.data?.response?.body?.stations?.minByOrNull { it.tm ?: Double.MAX_VALUE }
    }

    private suspend fun getLatestAirQualityData(stationName: String) : AirQuality? {
        return getRealtimeAirQualitiesUseCase.execute(stationName)
            .data
            ?.response
            ?.body
            ?.airQualities
            ?.firstOrNull()
    }

    private fun checkNetworkState() : Boolean {
        return networkManager.checkNetworkState()
    }
}