package org.rs3emulator.server.channel.handshake

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder

class HandshakeDecoder : ByteToMessageDecoder() {
    override fun decode(ctx: ChannelHandlerContext, buffer: ByteBuf, out: MutableList<Any>) {
        println(buffer.capacity())
        println("Opcode " + buffer.readUnsignedByte())
    }
}