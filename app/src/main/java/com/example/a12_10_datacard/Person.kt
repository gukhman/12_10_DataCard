package com.example.a12_10_datacard

import java.io.Serializable
import java.time.LocalDate

data class Person(
    val firstName: String,
    val secondName: String,
    val date: LocalDate,
    val image: String
) : Serializable