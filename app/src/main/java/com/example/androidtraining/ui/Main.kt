package com.example.androidtraining.ui

fun main() {
    print(myArray())
}
fun myArray(): List<Int> {
    val listNumber = mutableListOf<Int>()
    var counter = 0
    while (counter < 10) {
          counter++
        listNumber.add(counter)
    }
    return listNumber
}