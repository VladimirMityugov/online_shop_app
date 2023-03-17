package com.example.onlineShopApp.presentation.di

import android.content.Context
import androidx.room.Room
import com.chocky_development.data.local.ShopDataBase
import com.chocky_development.data.local.dao.FavoriteGoodsDao
import com.chocky_development.data.local.dao.ShopDao
import com.chocky_development.data.remote.ShopApi
import com.chocky_development.data.repositories.RepositoryLocalImpl
import com.chocky_development.data.repositories.RepositoryNetworkImpl
import com.chocky_development.domain.repositories.RepositoryLocal
import com.chocky_development.domain.repositories.RepositoryNetwork
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
object DataModule {

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

    @Provides
    @Singleton
    fun provideRepositoryLocal(
        shopDao: ShopDao,
        favoriteGoodsDao: FavoriteGoodsDao
    ): RepositoryLocal {
        return RepositoryLocalImpl(
            shopDao, favoriteGoodsDao
        )
    }

    @Provides
    @Singleton
    fun provideRepositoryNetwork(shopApi: ShopApi): RepositoryNetwork {
        return RepositoryNetworkImpl(
            shopApi
        )
    }
}