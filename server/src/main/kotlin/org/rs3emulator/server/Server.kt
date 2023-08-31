package org.rs3emulator.server

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.nio.NioEventLoopGroup
import org.rs3emulator.server.channel.ServerChannelInitializer

class Server(val port: Int) : Runnable {

    val bossGroup = NioEventLoopGroup()
    val workerGroup = NioEventLoopGroup()

    override fun run() {
        val bootstrap = ServerBootstrap()
            .group(bossGroup, workerGroup)
            .childHandler(ServerChannelInitializer())
        val future = bootstrap.bind(port).sync()
        future.channel().closeFuture().sync()
    }
}