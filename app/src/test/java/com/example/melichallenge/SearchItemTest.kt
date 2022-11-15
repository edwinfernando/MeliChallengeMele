package com.example.melichallenge

import com.example.melichallenge.provider.MeliProvider
import com.example.melichallenge.repository.SearchItemsRepositoryImp
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchItemsTest {
    private val mockWebServer = MockWebServer()

    private val meliProvider = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MeliProvider::class.java)

    private val searchItemsRepository = SearchItemsRepositoryImp(meliProvider)

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `Search items successful`() {
        mockWebServer.enqueueResponse("search_item_successful.json")

        runBlocking {
            val itemSearch = searchItemsRepository.getItemsSearchedApi("camara")
            Assert.assertEquals(50, itemSearch.size)
        }
    }

    @Test
    fun `Search items empty`() {
        mockWebServer.enqueueResponse("search_item_empty.json")

        runBlocking {
            val itemSearch = searchItemsRepository.getItemsSearchedApi("fasdfasf")
            Assert.assertEquals(0, itemSearch.size)
        }
    }
}