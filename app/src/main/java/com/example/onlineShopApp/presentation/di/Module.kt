package com.example.onlineShopApp.presentation.di

import android.content.Context
import androidx.room.Room
import com.example.onlineShopApp.data.local.ShopDataBase
import com.example.onlineShopApp.data.remote.ShopApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    @Singleton
    fun provideMovieDatabase(@ApplicationContext context: Context): ShopDataBase {
        return Room.databaseBuilder(
            context,
            ShopDataBase::class.java,
            "shopDataBase"
        ).build()
    }

    @Provides
    @Singleton
    fun provideShopDao(dataBase: ShopDataBase) = dataBase.shopDao()

    @Provides
    @Singleton
    fun provideFavoritesDao(dataBase: ShopDataBase) = dataBase.favoritesDao()


    @Provides
    @Singleton
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