package com.example.crewmovies.core.data.apiservice

import com.example.crewmovies.core.data.models.PeopleResponseDataModel
import retrofit2.http.GET
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query

interface MovieService {
    //https://api.themoviedb.org/3/search/person?api_key=7742db1161b96d5da49d8268ec472eae&query=Spielberg%20Steven
    //https://developers.themoviedb.org/3/search/search-people  (api doku für obere url)
    @GET("3/search/person")
    //fun getPeopleDetailsByName(@Query("api_key") app_key: String, @Query("query") personName: String): Flow<PeopleResponseDataModel>
    //first query puts ? then their name and then the value and all the other querys after that put & their name and the value
    suspend fun getPeopleDetailsByName(@Query("api_key") app_key: String, @Query("query") personName: String): PeopleResponseDataModel

    //https://api.themoviedb.org/3/person/popular?api_key=7742db1161b96d5da49d8268ec472eae
    //https://developers.themoviedb.org/3/people/get-popular-people  (api doku für obere url)
    @GET("3/person/popular")
    fun getPopularPeople(@Query("api_key") app_key: String): Flow<PeopleResponseDataModel>
}

/*
interface WeatherService {
    //https://openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=b6907d289e10d714a6e88b30761fae22
    @GET("data/2.5/weather?")
    fun getCurrentWeatherData(@Query("lat") lat: String, @Query("lon") lon: String, @Query("APPID") app_id: String): Call<WeatherResponse>
}
 */