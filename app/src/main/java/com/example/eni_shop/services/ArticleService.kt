package com.example.eni_shop.services

import com.example.eni_shop.bo.Article
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ArticleService {

    companion object {

        val BASE_URL = "https://fakestoreapi.com"

        //création de l'instance de Moshi qui converti le JSON en objet Kotlin
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        //création de l'instance de Retrofit qui permet de lancer des appels aux Api
        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .build()
    }

    @GET("/products")
    suspend fun getAllArticles(): List<Article>

    @GET("/products/{id}")
    suspend fun getOneArticle(@Path("id") id: Int): Article

    @POST("/products")
    suspend fun addArticle(@Body article: Article) : Article

    object ArticleAPI {
        val retrofitService: ArticleService by lazy { retrofit.create(ArticleService::class.java) }
    }

}