package com.example.models

import org.bson.codecs.pojo.annotations.BsonId

data class User(
    @BsonId
    val id: Int,
    val name: String,
    val age: Int
)
