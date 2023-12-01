package com.amonteiro.a2023_12_kampus


//Ici je n'ai mis que ce qui est utile pour l'affichage demandé mais on peut tout mettre
data class WeatherBean(
    var name: String,
    var main: TempBean,
    var wind : WindBean
)

data class TempBean(var temp: Double)
data class WindBean(var speed: Double)