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

        get("/users") {
            call.respond(users)
        }

        post("/user") {
            val requestBody = call.receive<User>()
            val user = requestBody.copy(id = users.size.plus(1))
            users.add(user)
            call.respond(user)
        }

        get("/user") {
            val id = call.request.queryParameters["id"]
            val user = users.find { it.id == id?.toInt() }
            val response = user ?: "User not found"
            call.respond(response)
        }

        delete("/user") {
            val id = call.request.queryParameters["id"]
            users.removeIf{
                it.id == id?.toInt()
            }
            call.respond("User was deleted")
        }
    }
}
