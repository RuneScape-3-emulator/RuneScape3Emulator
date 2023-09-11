package org.rs3emulator.server.channel

import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel
import org.rs3emulator.server.channel.handshake.HandshakeDecoder

class ServerChannelInitializer : ChannelInitializer<SocketChannel>() {
    override fun initChannel(ch: SocketChannel) {
        println("Initializing connecting channel.")
        ch.pipeline().addLast("decoder", HandshakeDecoder())
        ch.pipeline().addLast("handler", ChannelHandler())
    }
}