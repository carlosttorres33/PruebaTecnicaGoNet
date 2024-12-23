package com.carlostorres.pruebatecnicagonet.home.domain.repository

import com.carlostorres.pruebatecnicagonet.home.data.remote.model.ImageResponse
import retrofit2.Response

interface ImageRepo {
    suspend fun getRandomImage(): Response<ImageResponse>
}