package com.example.finedust.data.mapper

import com.example.finedust.data.util.Resource
import retrofit2.Response

inline fun<reified T> responseToResource(response: Response<T>) : Resource<T> {
    if (response.isSuccessful) {
        response.body()?.let {
            return Resource.Success(it)
        }
    }
    return Resource.Error(response.message())
}