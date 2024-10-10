package ru.example.composeui

import android.app.Application
import ru.example.composeui.data.PlatformConfiguration
import ru.example.composeui.data.di.PlatformSDK

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        PlatformSDK.init(
            configuration =
            PlatformConfiguration(
                androidContext = applicationContext,
                version = BuildConfig.VERSION_NAME,
                code = BuildConfig.VERSION_CODE.toString(),
            ),
        )
    }
}