package com.armutyus.rickandmortyproject.repo

import com.armutyus.rickandmortyproject.api.CharactersAPI
import com.armutyus.rickandmortyproject.model.CharacterDetails
import com.armutyus.rickandmortyproject.model.DetailResult
import com.armutyus.rickandmortyproject.model.Result
import com.armutyus.rickandmortyproject.util.Resource
import javax.inject.Inject

class CharacterRepo @Inject constructor(
    private val charactersAPI: CharactersAPI
) : CharacterRepoInterface {
    override suspend fun characterMain(): Resource<CharacterDetails> {
        return try {
            val response = charactersAPI.getCharacters()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error", null)
            } else {
                Resource.error("Error", null)
            }
        } catch (e: Exception) {
            Resource.error("No data!", null)
        }
    }

    /*override suspend fun characterDetail(charId: Int): Resource<DetailResult> {

        val response = try {
            charactersAPI.getCharactersById(charId)
            println("repo:" + charId)
        } catch(e: Exception) {
            return Resource.error("Error",null)
        }
        return  Resource.success(response)
    }*/
}