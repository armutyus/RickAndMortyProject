package com.armutyus.rickandmortyproject.api

import com.armutyus.rickandmortyproject.model.CharacterDetails
import com.armutyus.rickandmortyproject.model.DetailResult
import com.armutyus.rickandmortyproject.model.Result
import com.armutyus.rickandmortyproject.util.Resource
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CharactersAPI {

    @GET("character")
    suspend fun getCharacters(): Response<CharacterDetails>

    @GET("character/{id}")
    suspend fun getCharactersById(@Path(value = "id") key: Int): DetailResult

}