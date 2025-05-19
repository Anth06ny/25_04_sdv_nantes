package com.example.a25_04_sdv_nantes.model

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.jvm.java

//Utilisation
fun main() {
    //Lance la requête et met le corps de la réponse dans html
    //var html : String = WeatherRepository.sendGet("https://www.google.fr")
    //Affiche l'HTML
    //println("html=$html")

   val list = WeatherRepository.loadWeather("Nantes")

    for(city in list){
        println("""
            Il fait ${city.main.temp}° à ${city.name} (id=${city.id}) avec un vent de ${city.wind.speed} m/s
            -Description : ${city.weather.firstOrNull()?.description ?: "-"}
            -Icône : ${city.weather.firstOrNull()?.icon ?: "-"}
        """.trimIndent())
    }
}

object WeatherRepository {
    val client = OkHttpClient()
    val gson = Gson()


    fun loadWeather(cityName:String): List<WeatherBean> {
       val json = sendGet("https://api.openweathermap.org/data/2.5/find?q=$cityName&cnt=5&appid=b80967f0a6bd10d23e44848547b26550&units=metric&lang=fr")

        val result = gson.fromJson(json, WeatherAPIResult::class.java)
        //modifie le nom de l'image par l'url complète
        result.list.forEach {
            it.weather.forEach {
                it.icon = "https://openweathermap.org/img/wn/${it.icon}@4x.png"
            }
        }

        return result.list
    }


    //Méthode qui prend en entrée une url, execute la requête
    //Retourne le code HTML/JSON reçu
    fun sendGet(url: String): String {
        println("url : $url")
        //Création de la requête
        val request = Request.Builder().url(url).build()
        //Execution de la requête
        return client.newCall(request).execute().use { //it:Response
            //use permet de fermer la réponse qu'il y ait ou non une exception
            //Analyse du code retour
            if (!it.isSuccessful) {
                throw Exception("Erreur serveur :${it.code}\n${it.body.string()}")
            }
            //Résultat de la requête
            it.body.string()
        }
    }
}

data class WeatherAPIResult(val list:List<WeatherBean>)
data class WeatherBean(val id :Long, val name:String, val wind:WindBean, val weather:List<DescriptionBean>, val main:TempBean )
data class WindBean(val speed :Double)
data class TempBean(val temp :Double)
data class DescriptionBean(val description :String, var icon:String)

