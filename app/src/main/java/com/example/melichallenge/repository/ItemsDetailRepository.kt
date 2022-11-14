package com.example.melichallenge.repository

import android.util.Log
import com.example.melichallenge.exception.NoConnectivityException
import com.example.melichallenge.model.ItemDetailsAndDescription
import com.example.melichallenge.model.ItemDescriptionApiResponse
import com.example.melichallenge.model.ItemDetailsApiResponse
import com.example.melichallenge.provider.MeliProvider
import javax.inject.Inject

interface ItemsDetailRepository{
    suspend fun getItemDetailsApi(idItem: String) : ItemDetailsApiResponse
    suspend fun getItemDescriptionApi(idItem: String) : ItemDescriptionApiResponse
    suspend fun getDetailsAndDescriptionItemApi(idItem: String): ItemDetailsAndDescription
}

class ItemsDetailRepositoryImp @Inject constructor(
    private val meliProvider: MeliProvider
): ItemsDetailRepository{

    private var itemDetails: ItemDetailsApiResponse? = null
    private var itemDescription: ItemDescriptionApiResponse? = null

    override suspend fun getItemDetailsApi(idItem: String): ItemDetailsApiResponse {
        try {
            val itemDetailResponse = meliProvider.getItemDetail(idItem).body()!!
            if (itemDetailResponse.status == "404"){
                throw Exception(itemDetailResponse.message)
            }

            itemDetails = itemDetailResponse
        }catch (e: NoConnectivityException){
            Log.e("Ocurrio un error en la conexión: ", e.toString())
        }catch (e: Exception){
            Log.e("Ocurrio un error consultando el detalle del item: ", e.toString())
        }

        return itemDetails!!
    }

    override suspend fun getItemDescriptionApi(idItem: String): ItemDescriptionApiResponse {
        try {
            val itemDescriptionResponse = meliProvider.getItemDescription(idItem).body()!!
            if (itemDescriptionResponse.status == "404"){
                throw Exception(itemDescriptionResponse.message)
            }
            itemDescription = itemDescriptionResponse
        }catch (e: NoConnectivityException){
            Log.e("Ocurrio un error en la conexión: ", e.toString())
        }catch (e: Exception){
            Log.e("Ocurrio un error consultando la descripcion del item: ", e.toString())
        }

        return itemDescription!!
    }

    override suspend fun getDetailsAndDescriptionItemApi(idItem: String): ItemDetailsAndDescription {
        val itemDetail = getItemDetailsApi(idItem)
        val itemDescription = getItemDescriptionApi(idItem)

        return ItemDetailsAndDescription(itemDetails = itemDetail, description = itemDescription)
    }
}