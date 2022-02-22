package com.armutyus.rickandmortyproject.api

import com.armutyus.rickandmortyproject.model.CharacterDetails
import io.reactivex.Single
import retrofit2.http.GET

interface CharactersAPI {

    @GET("character")

    fun getCharacters(): Single<List<CharacterDetails>>

}