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

val client = KMongo.createClient().coroutine
val database = client.getDatabase("users_db2")
val collection = database.getCollection<User>()

fun main() {

    embeddedServer(Netty, port = 8082, host = "0.0.0.0") {
        configureRouting(collection)
        configureSerialization()
    }.start(wait = true)
}