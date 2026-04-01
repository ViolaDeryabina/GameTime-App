package com.example.gametime_app.presentation

import android.app.Application
import androidx.compose.runtime.Applier
import io.appmetrica.analytics.AppMetrica
import io.appmetrica.analytics.AppMetricaConfig

class GameApp: Application() {
    override fun onCreate() {
        super.onCreate()

        val config = AppMetricaConfig.newConfigBuilder("677cb0ca-031e-4ea2-8b5d-c6b76b4965b6")
            .withLogs()
            .build()

        AppMetrica.activate(this, config)
    }
}