package com.armutyus.rickandmortyproject.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.armutyus.rickandmortyproject.api.CharactersAPI
import com.armutyus.rickandmortyproject.model.CharacterDetails
import com.armutyus.rickandmortyproject.util.Resource
import com.bumptech.glide.RequestManager
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersAPI: CharactersAPI
): ViewModel() {
    private val disposable = CompositeDisposable()
    val charactersList = MutableLiveData<List<CharacterDetails>>()
    val characterError = MutableLiveData<Boolean>()
    val characterLoading = MutableLiveData<Boolean>()

    private fun getDataFromApi() {

        disposable.add(charactersAPI.getCharacters()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<CharacterDetails>>() {
                override fun onSuccess(t: List<CharacterDetails>) {
                    characterLoading.value = true
                    val response = t
                    if (response.isNotEmpty()) {
                        Resource.success(t)
                    }
                }

                override fun onError(e: Throwable) {
                    characterError.value = true
                    characterLoading.value = false
                    Resource.error("Error!", null)
                    e.printStackTrace()
                }

            })
        )

    }

    private fun showCharacters(characterShow: CharacterDetails) {
        charactersList.value = listOf(characterShow)
        characterLoading.value = false
        characterError.value = false
    }

}