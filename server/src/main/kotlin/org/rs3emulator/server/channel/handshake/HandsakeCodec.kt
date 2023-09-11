package org.rs3emulator.server.channel.handshake

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageCodec

class HandsakeCodec : ByteToMessageCodec<String>() {
    override fun encode(ctx: ChannelHandlerContext, msg: String, out: ByteBuf) {

    }

    override fun decode(ctx: ChannelHandlerContext, buffer: ByteBuf, out: MutableList<Any>) {

    }
}