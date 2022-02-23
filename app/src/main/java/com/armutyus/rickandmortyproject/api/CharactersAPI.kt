package com.armutyus.rickandmortyproject.api

import com.armutyus.rickandmortyproject.model.CharacterDetails
import retrofit2.Response
import retrofit2.http.GET

interface CharactersAPI {

    @GET("character")
    suspend fun getCharacters(): Response<CharacterDetails>

}