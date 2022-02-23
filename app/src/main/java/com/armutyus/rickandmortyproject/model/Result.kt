package com.armutyus.rickandmortyproject.model

data class Result(
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val status: String,
)