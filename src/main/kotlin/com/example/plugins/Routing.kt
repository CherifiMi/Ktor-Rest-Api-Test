package com.example.plugins

import com.example.models.User
import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.response.*
import io.ktor.request.*
import org.litote.kmongo.coroutine.CoroutineCollection


//val users = mutableListOf<User>()


fun Application.configureRouting(collection: CoroutineCollection<User>) {
    install(ContentNegotiation) {
        gson()
    }

    routing {

        get("/"){
            call.respond("HILLO")
        }

        get("/users") {
            val users = collection.find().toList()
            call.respond(users)
        }

        post("/user") {
            call.parameters
            val requestBody = call.receive<User>()
            val isSuccess = collection.insertOne(requestBody).wasAcknowledged()
            call.respond(isSuccess)
        }
    }
}