package com.carlostorres.pruebatecnicagonet.home.data.remote.repository

import com.carlostorres.pruebatecnicagonet.home.data.remote.ImageService
import com.carlostorres.pruebatecnicagonet.home.data.remote.model.ImageResponse
import com.carlostorres.pruebatecnicagonet.home.domain.repository.ImageRepo
import retrofit2.Response

class ImageRepoImpl(
    private val imageService: ImageService
) : ImageRepo {
    override suspend fun getRandomImage(): Response<ImageResponse> {
        return imageService.getRandomImage()
    }
}