package com.carlostorres.pruebatecnicagonet.home.data.remote

import com.carlostorres.pruebatecnicagonet.home.data.remote.model.ImageResponse
import com.carlostorres.pruebatecnicagonet.home.domain.repository.ImageRepo
import retrofit2.Response

class RemoteImageDataSource(
    private val imageRepo: ImageRepo
) {
    suspend fun getRandomImage(): Response<ImageResponse> {
        return imageRepo.getRandomImage()
    }
}