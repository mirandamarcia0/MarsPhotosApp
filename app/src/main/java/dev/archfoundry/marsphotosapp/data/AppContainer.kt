package dev.archfoundry.marsphotosapp.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dev.archfoundry.marsphotosapp.network.MarsApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

interface AppContainer {
    val MarsPhotosRepository : MarsPhotosRepository
}

class DefaultAppContainer : AppContainer{
    private val baseUrl =
        "https://android-kotlin-fun-mars-server.appspot.com/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()

    val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }
    override val MarsPhotosRepository: MarsPhotosRepository by lazy {
        NetworkMarsPhotosRepository (retrofitService)

    }


}