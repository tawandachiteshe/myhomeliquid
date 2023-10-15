package co.za.kgopelang.bans.service

import co.za.kgopelang.bans.data.ConnectionInfo


interface LiquidService {

    suspend fun GetConnectedServiceDetails(): ConnectionInfo

}