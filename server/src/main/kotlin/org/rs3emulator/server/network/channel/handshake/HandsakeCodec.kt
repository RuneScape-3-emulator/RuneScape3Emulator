package org.rs3emulator.server.network.channel.handshake

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageCodec
import org.rs3emulator.server.network.channel.ResultType
import org.rs3emulator.server.network.channel.login.LoginCodec
import org.rs3emulator.server.network.channel.login.LoginHandler
import java.util.concurrent.ThreadLocalRandom

class HandsakeCodec : ByteToMessageCodec<HandshakeResponse>() {
    override fun encode(ctx: ChannelHandlerContext, msg: HandshakeResponse, out: ByteBuf) {
        if(!out.isWritable) {
            println("[Handshake]: Buffer is not writable.")
            return
        }
        println("[Handshake]: Encoding response @ $msg")
        out.writeByte(msg.result.id)
        out.writeLong(ThreadLocalRandom.current().nextLong())
        when(msg.handshake) {
            HandshakeType.LOGIN -> ctx.pipeline().apply {
                replace("handshake.codec", "login.codec", LoginCodec())
                replace("handshake.handler", "login.handler", LoginHandler())
            }
            HandshakeType.UPDATE_CONNECTION -> TODO()
            HandshakeType.UPDATE_WEB_CONNECTION -> TODO()
        }
    }

    override fun decode(ctx: ChannelHandlerContext, buffer: ByteBuf, out: MutableList<Any>) {
        if(!buffer.isReadable) {
            println("Buffer isn't readable!")
            return
        }

        buffer.markReaderIndex()
        val opcode = buffer.readUnsignedByte().toInt()
        println("Handshake Opcode @ $opcode")
        if(opcode == 14) {
            out.add(HandshakeResponse(HandshakeType.LOGIN, ResultType.SUCCESS))
        } else {
            out.add(HandshakeResponse(HandshakeType.LOGIN, ResultType.CONNECTION_FAILED_LOGINSERVER))
        }
    }
}