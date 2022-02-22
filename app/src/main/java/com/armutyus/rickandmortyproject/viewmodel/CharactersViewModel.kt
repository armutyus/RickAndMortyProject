package com.armutyus.rickandmortyproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.armutyus.rickandmortyproject.model.CharacterDetails
import com.armutyus.rickandmortyproject.repo.CharacterRepoInterface
import com.armutyus.rickandmortyproject.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val repository: CharacterRepoInterface
) : ViewModel() {

    private val characters = MutableLiveData<Resource<CharacterDetails>>()
    val charList: LiveData<Resource<CharacterDetails>>
        get() = characters

    fun makeCharacters(imageString: String, nameString: String) {

        viewModelScope.launch {
            val response = repository.characterMain(imageString, nameString)
            characters.value = response
        }
    }

}