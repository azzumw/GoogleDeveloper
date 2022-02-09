package com.example.marsphotos

import com.example.marsphotos.network.MarsApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import junit.framework.Assert.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MarsApiServiceTests :BaseTest(){
    private lateinit var service : MarsApiService

    @Before
    fun setUp() {
        //For this test, we won't use a URL for our network requests. This is where MockWebServer comes in handy.

        //MockWebServer has a function called url() that specifies which URL we want to intercept.

        //The url() function takes a string that represents that fake URL and it returns an HttpUrl object
        val url = mockWebServer.url("/")

        service = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(
                MoshiConverterFactory.create(
                Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
            ))
            .build()
            .create(MarsApiService::class.java)

    }

    @Test
    fun api_service() {
        enqueue("mars_photos.json")

        runBlocking {
            val apiResponse = service.getPhotos()
            assertNotNull(apiResponse)
            assertTrue("The list was empty", apiResponse.isNotEmpty())
            assertEquals("The IDs did not match", "424905", apiResponse[0].id)
        }

    }
}