package com.igorj.minichallenges.api

import com.igorj.minichallenges.model.AstrosResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class AstrosApi {
    private val client = HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun getAstros(): AstrosResponse {
        return client.get(BASE_URL).body()
    }
}

private const val BASE_URL = "http://api.open-notify.org/astros.json"