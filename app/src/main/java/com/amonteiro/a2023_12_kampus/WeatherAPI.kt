package com.amonteiro.a2023_12_kampus

import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.Request

const val URL_API_WEATHER =
    "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=b80967f0a6bd10d23e44848547b26550&units=metric&lang=fr"

fun main() {
   // println(WeatherAPI.loadWeather("Toulouse"))
    runBlocking {
        launch {
            WeatherAPI.getWeathers("Nice", "Toulouse", "Paris", "adohdfaiophd", "Toulon")
                .filter { it.wind.speed <10 }
                .catch { it.printStackTrace() }
                .collect {
                    println("-Il fait ${it.main.temp}° à ${it.name} avec un vent de ${it.wind.speed} m/s")
                }
        }
    }

    println("fin")
}

object WeatherAPI {

    val client = OkHttpClient()
    val gson = Gson()

    fun getWeathers(vararg cities: String) = flow<WeatherBean> {
        cities.forEach {
            emit(loadWeather(it))
            delay(500)
        }
    }

    fun loadWeather(city: String): WeatherBean {

        Thread.sleep(1000)
        val json = sendGet(URL_API_WEATHER.format(city))
        return gson.fromJson(json, WeatherBean::class.java)
    }

    fun sendGet(url: String): String {
        println("url : $url")
        //Création de la requête
        val request = Request.Builder().url(url).build()
        //Execution de la requête
        return client.newCall(request).execute().use {
            //Analyse du code retour
            if (!it.isSuccessful) {
                throw Exception("Réponse du serveur incorrect :${it.code}")
            }
            //Résultat de la requête
            it.body.string()
        }
    }
}