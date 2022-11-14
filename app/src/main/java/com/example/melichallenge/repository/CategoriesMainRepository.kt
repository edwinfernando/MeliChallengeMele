package com.example.melichallenge.repository

import android.util.Log
import com.example.melichallenge.exception.NoConnectivityException
import com.example.melichallenge.model.Categories
import com.example.melichallenge.provider.MeliProvider
import javax.inject.Inject

interface CategoriesMainRepository {
    suspend fun getCategoriesApi() : List<Categories>
}

class CategoriesMainRepositoryImp @Inject constructor(
    private val meliProvider: MeliProvider
) : CategoriesMainRepository {

    private var lCategories: List<Categories> = emptyList()

    override suspend fun getCategoriesApi(): List<Categories> {
        try {
            val categoriesResponse = meliProvider.getCategories().body() ?: throw Exception()
            lCategories = categoriesResponse
        }catch (e : NoConnectivityException){
            Log.e("Ocurrio un error en la conexión: ", e.toString())
        }catch (e: Exception){
            Log.e("Ocurrio un error consultando categorias: ", e.toString())
        }
        return lCategories
    }

}