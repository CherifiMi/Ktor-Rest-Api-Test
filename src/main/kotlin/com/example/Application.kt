package com.example

import com.example.models.User
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

fun main() {

    val client = KMongo.createClient().coroutine
    val database = client.getDatabase("users_db")
    val col = database.getCollection<User>()

    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting(col)
    }.start(wait = true)
}