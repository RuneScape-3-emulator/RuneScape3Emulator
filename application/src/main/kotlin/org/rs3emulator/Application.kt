package org.rs3emulator

import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.rs3emulator.server.Server
import org.rs3emulator.server.game.World
import java.util.concurrent.Executors

object Application : KoinComponent {

    val executors = Executors.newFixedThreadPool(2)

    @JvmStatic
    fun main(args: Array<String>) {

        //Dependency Injection
        startKoin {
            modules(module {
                single { World() }
            })
        }
        executors.submit(Server(43594))
        executors.submit(get<World>())
    }

}