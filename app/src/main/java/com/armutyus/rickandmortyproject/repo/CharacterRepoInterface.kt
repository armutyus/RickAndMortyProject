package com.armutyus.rickandmortyproject.repo

import com.armutyus.rickandmortyproject.model.CharacterDetails
import com.armutyus.rickandmortyproject.util.Resource

interface CharacterRepoInterface {

    suspend fun characterMain(imageString: String, nameString: String) : Resource<CharacterDetails>
}