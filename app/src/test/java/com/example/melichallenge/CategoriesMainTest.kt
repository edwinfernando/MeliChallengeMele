package com.example.melichallenge

import com.example.melichallenge.provider.MeliProvider
import com.example.melichallenge.repository.CategoriesMainRepositoryImp
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.nio.charset.StandardCharsets

class CategoriesMainTest {
    private val mockWebServer = MockWebServer()

    private val meliProvider = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MeliProvider::class.java)

    private val categoriesMainRepository = CategoriesMainRepositoryImp(meliProvider)

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `Search items successful`() {
        mockWebServer.enqueueResponse("categories_main_successful.json")

        runBlocking {
            categoriesMainRepository.getCategoriesApi()
        }
    }
}

fun MockWebServer.enqueueResponse(filePath: String) {
    val inputStream = javaClass.classLoader?.getResourceAsStream(filePath)
    val source = inputStream?.source()?.buffer()
    source?.let {
        enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(it.readString(StandardCharsets.UTF_8))
        )
    }
}