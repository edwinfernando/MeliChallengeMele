package com.example.melichallenge.model

data class ItemSearchApiResponse(
    val paging: Paging,
    val results: List<Items>? = null
)