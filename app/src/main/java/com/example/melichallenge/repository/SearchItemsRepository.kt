package com.example.melichallenge.repository

import android.util.Log
import com.example.melichallenge.exception.NoConnectivityException
import com.example.melichallenge.model.Items
import com.example.melichallenge.provider.MeliProvider
import javax.inject.Inject

interface SearchItemsRepository {
    suspend fun getItemsSearchedApi(search: String): List<Items>
    fun getTotalPaging(): Int
}

class SearchItemsRepositoryImp @Inject constructor(
    private val meliProvider: MeliProvider
) : SearchItemsRepository {

    private var lItemsSearched: List<Items> = emptyList()
    private var totalPaging: Int = 0

    override suspend fun getItemsSearchedApi(search: String): List<Items> {
        try {
            val itemsSearchResponse = meliProvider.searchItems(search).body()
            if (itemsSearchResponse?.results == null) {
                throw Exception()
            }
            lItemsSearched = itemsSearchResponse.results
            totalPaging = itemsSearchResponse.paging.total
        } catch (e: NoConnectivityException){
            Log.e("Ocurrio un error en la conexión: ", e.toString())
        } catch (e: Exception){
            Log.e("No se encontraron registro para esta consulta: ", e.toString())
        }
        return lItemsSearched
    }

    override fun getTotalPaging(): Int {
        return totalPaging
    }

}