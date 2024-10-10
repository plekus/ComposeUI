package ru.example.composeui.data.di

import com.russhwolf.settings.Settings
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.singleton

val dataModule = DI.Module("dataModule") {
    bind<Settings>() with singleton { Settings() }
}