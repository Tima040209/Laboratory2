package com.example.lab2.Model.entity

import java.util.UUID

data class Dog(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val barking: Int,
    val energy: Int,
    val protectiveness: Int,
    val image_link: String


)
