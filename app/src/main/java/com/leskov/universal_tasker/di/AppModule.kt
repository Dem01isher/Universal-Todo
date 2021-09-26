package com.leskov.universal_tasker.di

import android.content.Context
import androidx.room.Room
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.leskov.universal_tasker.BuildConfig
import com.leskov.universal_tasker.data.remote.RemoteDataSource
import com.leskov.universal_tasker.data.remote.RemoteDataSourceImpl
import com.leskov.universal_tasker.data.room.TaskDao
import com.leskov.universal_tasker.data.room.TaskDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 *  Created by Android Studio on 8/5/2021 3:25 PM
 *  Developer: Sergey Leskov
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): TaskDatabase =
        Room.databaseBuilder(context, TaskDatabase::class.java, "task_database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()

    @Provides
    @Singleton
    fun provideObjectDao(database: TaskDatabase): TaskDao =
        database.taskDao()


    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create()

    @Provides
    @Singleton
    fun provideConverterFactory(gson: Gson): Converter.Factory = GsonConverterFactory.create(gson)

    @Provides
    @Singleton
    fun provideRemoteDataSource(retrofit: Retrofit): RemoteDataSource =
        RemoteDataSourceImpl(retrofit)

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://universal-backend.herokuapp.com/")
            .client(okHttpClient)
            .build()


    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): RemoteDataSource {
        return retrofit.create(RemoteDataSource::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        interceptors: ArrayList<Interceptor>
    ): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
            .followRedirects(false)
        interceptors.forEach {
            clientBuilder.addInterceptor(it)
        }
        return clientBuilder.build()
    }


    @Singleton
    @Provides
    fun provideInterceptors(): ArrayList<Interceptor> {
        val interceptors = arrayListOf<Interceptor>()
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
        interceptors.add(loggingInterceptor)
        return interceptors
    }
}