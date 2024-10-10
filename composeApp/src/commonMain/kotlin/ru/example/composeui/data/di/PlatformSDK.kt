package ru.example.composeui.data.di

import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.direct
import org.kodein.di.singleton
import ru.example.composeui.data.PlatformConfiguration

object PlatformSDK {

    fun init(
        configuration: PlatformConfiguration
    ) {
        val sdkModule =
            DI.Module(
                name = "sdkModule",
                init = {
                    bind<PlatformConfiguration>() with singleton { configuration }
                },
            )

        Inject.createDependencies(
            DI {
                importAll(
                    sdkModule,
                    dataModule,
                )
            }.direct,
        )
    }
}