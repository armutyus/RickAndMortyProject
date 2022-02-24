package com.armutyus.rickandmortyproject.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.armutyus.rickandmortyproject.R
import com.armutyus.rickandmortyproject.adapter.CharactersAdapter
import com.armutyus.rickandmortyproject.databinding.FragmentCharactersBinding
import com.armutyus.rickandmortyproject.model.Result
import com.armutyus.rickandmortyproject.util.Status
import com.armutyus.rickandmortyproject.viewmodel.CharactersViewModel
import javax.inject.Inject

class CharactersFragment @Inject constructor(
    private val charactersAdapter: CharactersAdapter
) : Fragment(R.layout.fragment_characters) {

    private var fragmentBinding: FragmentCharactersBinding? = null
    lateinit var viewModel: CharactersViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[CharactersViewModel::class.java]
        val binding = FragmentCharactersBinding.bind(view)
        fragmentBinding = binding

        observeLiveData()

        binding.recyclerViewCharacters.adapter = charactersAdapter
        binding.recyclerViewCharacters.layoutManager = GridLayoutManager(requireContext(), 3)

    }

    override fun onResume() {
        super.onResume()
        viewModel.makeCharactersResponse()
    }

    private fun observeLiveData() {
        viewModel.charList.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {

                    val imageUrl = it.data?.results?.map { imageData -> imageData.image
                    }
                    val charNames = it.data?.results?.map { charName -> charName.name }

                    charactersAdapter.charImage = imageUrl!!
                    charactersAdapter.charName = charNames!!

                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_LONG)
                        .show()

                }

                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message ?: "Error", Toast.LENGTH_LONG)
                        .show()

                }

                Status.LOADING -> {

                }
            }

        })
    }

    override fun onDestroyView() {
        fragmentBinding = null
        super.onDestroyView()
    }
}