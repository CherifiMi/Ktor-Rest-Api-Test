package com.example.plugins

import com.example.models.User
import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.response.*
import io.ktor.request.*
import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.coroutine.CoroutineCollection


fun Application.configureRouting(collection: CoroutineCollection<User>) {

    routing {

        install(ContentNegotiation) {
            gson()
        }
        get("/") {
            call.respondText("Hello World!")
        }
        get("/users") {
            call.respond(collection.find().toList())
        }
        post("/user") {
            call.parameters
            val requestBody = call.receive<User>()
            val isSuccess = collection.insertOne(requestBody).wasAcknowledged()
            call.respond(isSuccess)
        }
    }
}