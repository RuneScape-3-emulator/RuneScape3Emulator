package org.rs3emulator.server.network.channel.login

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageCodec
import org.koin.core.component.KoinComponent
import org.rs3emulator.server.network.channel.ResultType
import org.rs3emulator.server.utilities.ByteBufExtensions.decipherXtea
import org.rs3emulator.server.utilities.ByteBufExtensions.readString

class LoginCodec : ByteToMessageCodec<LoginResponse>(), KoinComponent {

    override fun encode(ctx: ChannelHandlerContext, msg: LoginResponse, out: ByteBuf) {

    }

    override fun decode(ctx: ChannelHandlerContext, buffer: ByteBuf, out: MutableList<Any>) {
        if (!buffer.isReadable)
            return

        val opcode = buffer.readUnsignedByte().toInt()

        if (opcode != 16 && opcode != 18 && opcode != 19) {
            ctx.writeAndFlush(LoginResponse(ResultType.BAD_SESSION))
            ctx.close()
            return
        }

        if (buffer.readableBytes() < 2) {
            ctx.writeAndFlush(LoginResponse(ResultType.BAD_SESSION))
            ctx.close()
            return
        }

        val length = buffer.readUnsignedShort()
        if (buffer.readableBytes() != length) {
            ctx.writeAndFlush(LoginResponse(ResultType.BAD_SESSION))
            ctx.close()
            return
        }

        val major = buffer.readInt()
        val minor = buffer.readInt()

        if (major != 931) {
            println("WTF")
            ctx.writeAndFlush(LoginResponse(ResultType.VERSION_OUTDATED))
            ctx.close()
            return
        }

        if (opcode == 19) {
            handleLobbyLogin(ctx, buffer)
        }
    }

    private fun handleLobbyLogin(ctx: ChannelHandlerContext, buffer: ByteBuf) {
        val header = LoginHeader.readHeader(ctx, buffer)
        buffer.decipherXtea(header.issacSeeds)

        buffer.skipBytes(1) // legacy encoding!
        val name = buffer.readString()



    }
}