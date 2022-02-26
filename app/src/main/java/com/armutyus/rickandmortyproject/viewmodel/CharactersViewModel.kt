package com.armutyus.rickandmortyproject.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.armutyus.rickandmortyproject.adapter.CharactersAdapter
import com.armutyus.rickandmortyproject.model.CharacterDetails
import com.armutyus.rickandmortyproject.model.Result
import com.armutyus.rickandmortyproject.repo.CharacterRepoInterface
import com.armutyus.rickandmortyproject.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val repository: CharacterRepoInterface,
) : ViewModel() {

    private val characters = MutableLiveData<Resource<CharacterDetails>>()
    val charList: LiveData<Resource<CharacterDetails>>
        get() = characters

    fun makeCharactersResponse() {
        viewModelScope.launch {
            val response = repository.characterMain()
            characters.value = response
        }
    }

    /*private val charDetail = MutableLiveData<List<Result>>()
    val characterDetail: LiveData<List<Result>>
        get() = charDetail

    fun getDetailChar(id: Int) {
        viewModelScope.launch {
            val details = repository.characterDetail(id).data?.
            charDetail.value = details!!
            println("vm" + charDetail.value)
        }

    }*/

}