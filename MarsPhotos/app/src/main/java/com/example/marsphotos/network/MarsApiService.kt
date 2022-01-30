package com.example.marsphotos.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://android-kotlin-fun-mars-server.appspot.com"


private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

/**
 * Retrofit needs the base URI for the web service, and a converter factory to build a
 * web services API.
 * The converter tells Retrofit what to do with the data it gets back
 * from the web service. In this case, you want Retrofit to fetch a JSON response from
 * the web service, and return it as a String.
 * Retrofit has a ScalarsConverter that supports strings and other primitive types,
 * so you call addConverterFactory() on the builder with
 * an instance of ScalarsConverterFactory
 * */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

/**
 * defines how Retrofit talks to the web server using HTTP requests.
 */
interface MarsApiService {

    /**
     * Use the @GET annotation to tell Retrofit that this is GET request, and specify endpoint
     */
    @GET("photos")
    suspend fun getPhotos():List<MarsPhoto>
}

/**
 * initialize the Retrofit service
 */
object MarsApi {
 val retrofitService : MarsApiService by lazy {
     /**
      * Initialize the retrofitService variable using the retrofit.create() method with the MarsApiService interface.*/
     retrofit.create(MarsApiService::class.java)
 }
}