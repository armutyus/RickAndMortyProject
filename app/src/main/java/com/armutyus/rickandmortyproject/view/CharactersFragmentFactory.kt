package com.armutyus.rickandmortyproject.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.armutyus.rickandmortyproject.adapter.CharactersAdapter
import javax.inject.Inject

class CharactersFragmentFactory @Inject constructor(
    private val charactersAdapter: CharactersAdapter
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){

            CharactersFragment::class.java.name -> CharactersFragment(charactersAdapter)

            else -> super.instantiate(classLoader, className)
        }
    }

}