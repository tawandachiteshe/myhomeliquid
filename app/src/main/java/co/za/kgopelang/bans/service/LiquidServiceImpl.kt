package co.za.kgopelang.bans.service

import co.za.kgopelang.bans.data.ConnectionInfo
import co.za.kgopelang.bans.data.HttpRoutes
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.*

class LiquidServiceImpl(private val client: HttpClient) : LiquidService {

    override suspend fun GetConnectedServiceDetails(): ConnectionInfo {
        return client.get{
            url(HttpRoutes.CONNECTION_INFO)
        }.body()
    }

}