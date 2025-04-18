package com.example.a25_04_sdv_nantes.model

import java.util.Random

fun main() {
    var t1 = ThermometerBean(min = -20, max = 50, value = 0)
    println("Température de ${t1.value}") // 0

    //Cas qui marche
    t1.value = 10
    println("Température de ${t1.value}") // 10 attendu

    //Borne minimum
    t1.value = -30
    println("Température de ${t1.value}") // -20 attendu

    //Borne maximum
    t1.value = 100
    println("Température de ${t1.value}") // 50 attendu

    //Pour les plus rapides : Cas de départ
    t1 = ThermometerBean(min = -20, max = 50, value = -100)
    println("Température de ${t1.value}") // -20 attendu

    val t = ThermometerBean.getCelsiusThermometer()
}

data class PictureBean(val id:Int, val url: String, val title: String, val longText: String)

class RandomName {

    private val list = arrayListOf("bobby", "Bob", "Toto")
    private var oldValue =""

    fun add(name: String) =
        if (name.isNotBlank() && name !in list) {
            list.add(name)
        } else false

    fun next() = list.random()

    fun addAll(vararg names: String)  {
       for(n in names) {
           add(n)
       }
    }

    fun addAll2(vararg names: String)  = names.forEach { add(it) }

    fun nextDiff(): String {
        var newValue = next()
        while(newValue == oldValue) {
            newValue = next()
        }
        oldValue = newValue

        return newValue
    }

    fun nextDiff2() = list.filter { it != oldValue }.random().also { oldValue = it }

    fun next2() = Pair(nextDiff(), nextDiff())


}


class ThermometerBean(var min: Int, var max: Int, value: Int) {

    var value: Int = value.coerceIn(min, max)
        set(newValue) {
            field = newValue.coerceIn(min, max)
        }

    companion object {
        fun getCelsiusThermometer() = ThermometerBean(-30, 50, 0)
        fun getFahrenheitThermometer() = ThermometerBean(20, 120, 30)
    }
}

class PrintRandomIntBean(var max: Int) {
    private val random = Random()

    init {
        println(random.nextInt(max))
        println(random.nextInt(max))
        println(random.nextInt(max))
    }

    constructor() : this(10) {
        println("constructor")
        println(random.nextInt(max))
    }
}

class HouseBean(var color: String, width: Int, length: Int) {
    var surface = width * length

    fun print() = println("$color et ${surface}m²")

}

data class CarBean(var marque: String, var model: String?) {
    var color = ""
}