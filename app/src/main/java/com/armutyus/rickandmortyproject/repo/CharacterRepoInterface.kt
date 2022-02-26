package com.armutyus.rickandmortyproject.repo

import com.armutyus.rickandmortyproject.model.CharacterDetails
import com.armutyus.rickandmortyproject.model.DetailResult
import com.armutyus.rickandmortyproject.model.Result
import com.armutyus.rickandmortyproject.util.Resource
import retrofit2.Call
import retrofit2.Response

interface CharacterRepoInterface {

    suspend fun characterMain(): Resource<CharacterDetails>

    //suspend fun characterDetail(charId: Int): Resource<DetailResult>
}