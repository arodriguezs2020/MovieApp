@file:Suppress("BlockingMethodInNonBlockingContext")

package com.example.movieapp.core

import kotlinx.coroutines.coroutineScope
import java.net.InetSocketAddress
import java.net.Socket

// --- Esta clase la utilizamos para comprobar si el usuario tiene o no acceso a Internet --- //
object InternetCheck {

    suspend fun isNetworkAvalable() = coroutineScope {
        return@coroutineScope try {
            val sock = Socket()
            val socketAddress = InetSocketAddress("8.8.8.8",53)
            sock.connect(socketAddress, 2000)
            sock.close()
            true
        } catch (e: Exception) {
            false
        }
    }
}