package org.rs3emulator.server.network.channel.handshake

import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import org.rs3emulator.server.network.channel.ResultType

class HandshakeHandler : SimpleChannelInboundHandler<HandshakeResponse>() {
    override fun channelRead0(ctx: ChannelHandlerContext, msg: HandshakeResponse) {
        if(msg.result == ResultType.CONNECTION_FAILED_LOGINSERVER) {
            ctx.channel().close()
            return
        }

        ctx.writeAndFlush(msg)
    }
}