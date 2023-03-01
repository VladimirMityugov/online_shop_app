package com.example.onlineShopApp.data.di

import android.app.Application
import androidx.room.Room
import com.example.onlineShopApp.data.local.ShopDataBase
import com.example.onlineShopApp.data.remote.ShopApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


@Module
@InstallIn(ViewModelComponent::class)
object Module {

    @Provides
    @ViewModelScoped
    fun provideMovieDatabase(app: Application): ShopDataBase {
        return Room.databaseBuilder(
            app.applicationContext,
            ShopDataBase::class.java,
            "movieDataBase"
        ).build()
    }

    @Provides
    @ViewModelScoped
    fun provideShopDao(dataBase: ShopDataBase) = dataBase.shopDao()

    @Provides
    @ViewModelScoped
    fun provideFavoritesDao(dataBase: ShopDataBase) = dataBase.favoritesDao()


    @Provides
    @ViewModelScoped
    fun provideShopApi(): ShopApi {

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .baseUrl(ShopApi.BASE_URl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ShopApi::class.java)
    }
}