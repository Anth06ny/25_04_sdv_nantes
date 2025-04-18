package com.example.a25_04_sdv_nantes.exo

import com.example.a25_04_sdv_nantes.model.CarBean

class MyLiveData<T>(value:T) {
    var value:T = value
        set(newValue) {
            field = newValue
            action(newValue)
        }

    var action : (T)->Unit = {}
        set(newValue) {
            field = newValue
            action(value)
        }
}


fun main() {

    var toto = MyLiveData(CarBean("marque", "model"))


    toto.action = {
        println(it)
    }



    toto.value = CarBean("marque2", "model2")
    toto.value.marque = "marque3"
    toto.value =  toto.value.copy(marque = "marque3")





}




fun exo1(){
    var minToHour : ((Int?)->Pair<Int,Int>?)? = { if(it!=null) Pair(it/60, it%60) else null}
    val minToHour2 : (Int?)->Pair<Int,Int>? = { it?.let {Pair(it/60, it%60)  }  }


    println(minToHour?.invoke(123))
    println(minToHour?.invoke(null))
    minToHour = null

}