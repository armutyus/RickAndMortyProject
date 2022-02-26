package com.armutyus.rickandmortyproject.repo

import com.armutyus.rickandmortyproject.model.CharacterDetails
import com.armutyus.rickandmortyproject.util.Resource

interface CharacterRepoInterface {

    suspend fun characterMain(): Resource<CharacterDetails>
}