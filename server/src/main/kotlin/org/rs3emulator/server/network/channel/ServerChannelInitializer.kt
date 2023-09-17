package org.rs3emulator.server.network.channel

import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel
import io.netty.handler.logging.LogLevel
import io.netty.handler.logging.LoggingHandler
import org.rs3emulator.server.network.channel.handshake.HandsakeCodec
import org.rs3emulator.server.network.channel.handshake.HandshakeHandler

class ServerChannelInitializer : ChannelInitializer<SocketChannel>() {
    override fun initChannel(ch: SocketChannel) {
        //ch.pipeline().addLast(LoggingHandler(LogLevel.INFO))
        ch.pipeline().addLast("handshake.codec", HandsakeCodec())
        ch.pipeline().addLast("handshake.handler", HandshakeHandler())
    }
}