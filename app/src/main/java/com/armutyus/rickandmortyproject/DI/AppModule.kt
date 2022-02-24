package com.armutyus.rickandmortyproject.DI

import android.content.Context
import com.armutyus.rickandmortyproject.R
import com.armutyus.rickandmortyproject.api.CharactersAPI
import com.armutyus.rickandmortyproject.repo.CharacterRepo
import com.armutyus.rickandmortyproject.repo.CharacterRepoInterface
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun injectRetrofitApi(): CharactersAPI {

        val baseURL = "https://rickandmortyapi.com/api/"
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CharactersAPI::class.java)
    }

    @Singleton
    @Provides
    fun injectNormalRepo(api: CharactersAPI) = CharacterRepo(api) as CharacterRepoInterface

    @Singleton
    @Provides
    fun injectGlide(@ApplicationContext context: Context) = Glide
        .with(context).setDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
        )


}