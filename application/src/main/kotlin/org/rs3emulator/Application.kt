package org.rs3emulator

import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.rs3emulator.server.Server
import org.rs3emulator.server.game.World
import org.rs3emulator.server.network.RSAConfig
import java.math.BigInteger
import java.util.concurrent.Executors

object Application : KoinComponent {

    val executors = Executors.newFixedThreadPool(2)

    @JvmStatic
    fun main(args: Array<String>) {

        //Dependency Injection
        startKoin {
            modules(module {
                single { World() }
                single { RSAConfig(BigInteger("11303814051882129307438810182965307925073757654953135034502809144388448424805410546306650668668619918474928942584921579921806283651764687810978599658864278961475913223163454281241067102792237523802599833902897353688644612062290124782136334353345945042313920544411144522287172257215249291427721761133645830689"), BigInteger("96172667988861366794965247690639670970473692773291394360146774360091619423143215756627153689800771595104688836841231414167910997232987452300156365811111048012120562136211018813048258776889356108685839908591821176520356483726373227386287798546403102723438349960502736005023413114477489679088178624450797368749")) }
            })
        }
        Server(6969).run()
        //executors.submit(get<World>())
    }

}