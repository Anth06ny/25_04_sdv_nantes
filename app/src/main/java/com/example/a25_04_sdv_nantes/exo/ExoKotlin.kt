package com.example.a25_04_sdv_nantes.exo

import android.R.attr.text
import kotlin.random.Random
import kotlin.random.nextInt

var v2: String? = "Coucou"

fun main() {

    var v5 : Int? = null
    if(Random.nextBoolean()){
        v5 = Random.nextInt(10)
    }
    println("TODO")

    val nbCroissant = scanNumber("Nb Croissant : ")
    val nbBaguette = scanNumber("Nb baguette : ")
    val nbSandWitch = scanNumber("Nb Sandwitch : ")

    var res = boulangerie(nbCroissant,nbSandWitch, nbBaguette) //  boulangerie(1, 0, 3)
    println(res)
}
fun scanNumber(text:String) = scanText(text).toIntOrNull() ?: 0

fun scanText(text:String) :String {
    println(text)
    return readlnOrNull() ?: "-"
}

fun boulangerie(nbC:Int = 0, nbS:Int = 0 , nbB:Int= 0)
   =  nbC * PRICE_CROISSANT + nbS * PRICE_SANDWITCH + nbB * PRICE_BAGUETTE
