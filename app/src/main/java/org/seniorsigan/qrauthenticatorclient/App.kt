package org.seniorsigan.qrauthenticatorclient

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.google.gson.GsonBuilder
import com.squareup.okhttp.OkHttpClient
import java.security.SecureRandom
import java.util.*

const val TAG = "QRAuth"
const val RAW_TOKEN_INTENT = "RAW_TOKEN_INTENT"
const val SIGNUP_TOKEN_INTENT = "SIGNUP_TOKEN_INTENT"

class App: Application() {
    companion object {
        val CAN_USE_CAMERA = 0x2
        val CAN_USE_INTERNET = 0x3
        val gsonBuilder = GsonBuilder().registerTypeAdapter(Date::class.java, DateDeserializer())
        val gson = gsonBuilder.create()
        val httpClient = OkHttpClient()
        val keysGenerator = KeysGenerator()
        val secureRandom = SecureRandom()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}