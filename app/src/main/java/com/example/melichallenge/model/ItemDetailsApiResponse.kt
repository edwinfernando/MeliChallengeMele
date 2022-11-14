package com.example.melichallenge.model

data class ItemDetailsApiResponse(
    val id: String,
    val title: String,
    val subtitle: String,
    val price: String,
    val condition: String,
    val thumbnail: String,
    val secure_thumbnail: String,
    val pictures: List<Images>?,
    val status: String? = null,
    val message: String?
)
