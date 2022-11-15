package com.example.melichallenge

import com.example.melichallenge.provider.MeliProvider
import com.example.melichallenge.repository.ItemsDetailRepositoryImp
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertThrows
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ItemsDetailTest {
    private val mockWebServer = MockWebServer()

    private val meliProvider = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MeliProvider::class.java)

    private val itemsDetailRepository = ItemsDetailRepositoryImp(meliProvider)

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `Item detail successful`() {
        mockWebServer.enqueueResponse("item_detail_successful.json")

        runBlocking {
            val itemDetail = itemsDetailRepository.getItemDetailsApi("MLA1231759135")
            Assert.assertEquals("MLA1231759135", itemDetail.id)
        }
    }

    @Test
    fun `Item detail failure`() {
        mockWebServer.enqueueResponse("item_detail_failure.json")
        assertThrows(Exception::class.java){
            runBlocking {
                itemsDetailRepository.getItemDetailsApi("MLA123175913")
            }
        }
    }

    @Test
    fun `Item description successful`() {
        mockWebServer.enqueueResponse("item_description_successful.json")

        runBlocking {
            val itemDescription = itemsDetailRepository.getItemDescriptionApi("MLA1231759135")
            Assert.assertEquals("", itemDescription.text)
        }
    }

    @Test
    fun `Item description failure`() {
        mockWebServer.enqueueResponse("item_description_failure.json")
        assertThrows(Exception::class.java){
            runBlocking {
                itemsDetailRepository.getItemDescriptionApi("MLA123175913")
            }
        }
    }
}
