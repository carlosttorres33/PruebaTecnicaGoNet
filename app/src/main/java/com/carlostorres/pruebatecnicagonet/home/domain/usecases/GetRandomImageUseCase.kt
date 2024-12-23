package com.carlostorres.pruebatecnicagonet.home.domain.usecases

import com.carlostorres.pruebatecnicagonet.home.data.remote.RemoteImageDataSource
import com.carlostorres.pruebatecnicagonet.home.data.remote.model.ImageResponse
import retrofit2.Response

class GetRandomImageUseCase(
    private val remoteImageDataSource: RemoteImageDataSource
) {
    suspend operator fun invoke(): Response<ImageResponse> {
        return remoteImageDataSource.getRandomImage()
    }
}