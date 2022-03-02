package com.example.plugins

import com.example.models.User
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*

fun Application.configureRouting() {

    //-------------values
    val users = mutableListOf<User>()




    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get("/mito") {
            call.respond(users)
        }
        post("/user") {
            val requestBody = call.receive<User>()
            val user = requestBody.copy(id = users.size.plus(1))
            users.add(user)
            call.respond(user)
        }
    }
}
