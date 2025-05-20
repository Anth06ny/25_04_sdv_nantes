package com.example.a25_04_sdv_nantes.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a25_04_sdv_nantes.model.PictureBean
import com.example.a25_04_sdv_nantes.model.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

fun main() {


    val viewModel = MainViewModel()
    viewModel.loadWeathers("")
    //viewModel.loadWeathers("Paris")
    //attente
    while (viewModel.runInProgress.value) {
        Thread.sleep(500)
    }
    //Affichage de la liste et du message d'erreur
    println("List : ${viewModel.dataList.value}")
    println("ErrorMessage : ${viewModel.errorMessage.value}")
}

open class MainViewModel : ViewModel() {
    //MutableStateFlow est une donnée observable
    val dataList = MutableStateFlow(emptyList<PictureBean>())
    val runInProgress = MutableStateFlow(false)
    val errorMessage = MutableStateFlow("")

//    init {//Création d'un jeu de donnée au démarrage
//        //loadFakeData()
//        loadWeathers("Nice")
//    }


    fun loadFakeData(runInProgress :Boolean = false, errorMessage:String = "" ) {
        this.runInProgress.value = runInProgress
        this.errorMessage.value = errorMessage
        dataList.value = listOf(PictureBean(1, "https://picsum.photos/200", "ABCD", LONG_TEXT),
            PictureBean(2, "https://picsum.photos/201", "BCDE", LONG_TEXT),
            PictureBean(3, "https://picsum.photos/202", "CDEF", LONG_TEXT),
            PictureBean(4, "https://picsum.photos/203", "EFGH", LONG_TEXT)
        ).shuffled() //shuffled() pour avoir un ordre différent à chaque appel
    }

    fun loadWeathers(cityName: String) {

        runInProgress.value = true
        errorMessage.value = ""

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val list = WeatherRepository.loadWeather(cityName)

                val res = list.map { city ->
                    PictureBean(
                        id = city.id.toInt(),
                        city.weather.firstOrNull()?.icon ?: "",
                        city.name,
                        """
            Il fait ${city.main.temp}° à ${city.name} (id=${city.id}) avec un vent de ${city.wind.speed} m/s
            -Description : ${city.weather.firstOrNull()?.description ?: "-"}
            -Icône : ${city.weather.firstOrNull()?.icon ?: "-"}
        """.trimIndent()
                    )
                }
                dataList.value = res
            } catch (e: Exception) {
                e.printStackTrace()
                errorMessage.value = e.message ?: "Une erreur est survenue"
            } finally {
                runInProgress.value = false
            }
        }

    }
}

const val LONG_TEXT = """Le Lorem Ipsum est simplement du faux texte employé dans la composition
    et la mise en page avant impression. Le Lorem Ipsum est le faux texte standard
    de l'imprimerie depuis les années 1500"""