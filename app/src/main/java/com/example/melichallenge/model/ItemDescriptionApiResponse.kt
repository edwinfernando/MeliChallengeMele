package com.example.melichallenge.model

data class ItemDescriptionApiResponse(
    val text: String,
    val plain_text: String,
    val status: String? = null,
    val message: String?
)
