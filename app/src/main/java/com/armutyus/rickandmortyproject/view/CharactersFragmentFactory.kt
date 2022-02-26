package com.armutyus.rickandmortyproject.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.armutyus.rickandmortyproject.adapter.CharacterDetailsAdapter
import com.armutyus.rickandmortyproject.adapter.CharactersAdapter
import com.armutyus.rickandmortyproject.api.CharactersAPI
import javax.inject.Inject

class CharactersFragmentFactory @Inject constructor(
    private val charactersAdapter: CharactersAdapter,
    private val characterDetailsAdapter: CharacterDetailsAdapter
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){

            CharactersFragment::class.java.name -> CharactersFragment(charactersAdapter)
            CharacterDetailsFragment::class.java.name -> CharacterDetailsFragment(characterDetailsAdapter)

            else -> super.instantiate(classLoader, className)
        }
    }

}