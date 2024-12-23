package com.carlostorres.pruebatecnicagonet.home.data.remote

import com.carlostorres.pruebatecnicagonet.home.data.remote.model.ImageResponse
import retrofit2.Response
import retrofit2.http.GET

interface ImageService {
    @GET("random")
    suspend fun getRandomImage(): Response<ImageResponse>
}