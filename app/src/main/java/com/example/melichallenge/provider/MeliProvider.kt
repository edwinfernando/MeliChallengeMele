package com.example.melichallenge.provider

import com.example.melichallenge.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val SITE_ID = "MLA"

interface MeliProvider {

    @GET("sites/$SITE_ID/categories")
    suspend fun getCategories(): Response<List<Categories>>

    @GET("sites/$SITE_ID/search?")
    suspend fun searchItems(@Query("q") item: String): Response<ItemSearchApiResponse>

    @GET("items/{idItem}")
    suspend fun getItemDetail(@Path("idItem") idItem: String): Response<ItemDetailsApiResponse>

    @GET("items/{idItem}/description")
    suspend fun getItemDescription(@Path("idItem") idItem: String): Response<ItemDescriptionApiResponse>
}