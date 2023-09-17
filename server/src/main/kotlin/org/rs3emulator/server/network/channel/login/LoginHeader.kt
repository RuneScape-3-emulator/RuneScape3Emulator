package org.rs3emulator.server.network.channel.login

import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled
import io.netty.channel.ChannelHandlerContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.rs3emulator.server.network.RSAConfig
import org.rs3emulator.server.network.channel.ResultType
import org.rs3emulator.server.utilities.ByteBufExtensions.readString
import java.math.BigInteger

data class LoginHeader(val password: String, val clientSeed: Long, val issacSeeds: IntArray, val uuid: Long) {
    companion object : KoinComponent {

        val INVALID = LoginHeader("", 0, intArrayOf(), 0)

        fun readHeader(ctx: ChannelHandlerContext, buffer: ByteBuf) : LoginHeader {
            val secureBufferSize = buffer.readUnsignedShort()
            val rsa: RSAConfig = get()

            if (secureBufferSize > buffer.readableBytes()) {
                println("[Lobby Login]: SB Size does not match.")
                ctx.writeAndFlush(LoginResponse(ResultType.BAD_SESSION))
                ctx.close()
                return INVALID
            }

            val encryptedBuffer = ByteArray(secureBufferSize)
            buffer.readBytes(encryptedBuffer)

            val secureBuffer = Unpooled.wrappedBuffer(
                BigInteger(encryptedBuffer).modPow(rsa.exponent, rsa.modulus).toByteArray()
            )

            if (secureBuffer == null) {
                println("[Lobby Login]: SB null.")
                ctx.writeAndFlush(LoginResponse(ResultType.BAD_SESSION))
                ctx.close()
                return INVALID
            }

            val opcode = secureBuffer.readUnsignedByte().toInt()

            if (opcode != 10) {
                println("[Lobby Login]: Opcode != 10 @ $opcode")
                ctx.writeAndFlush(LoginResponse(ResultType.BAD_SESSION))
                ctx.close()
                return INVALID
            }

            val issacSeeds = IntArray(4) { secureBuffer.readInt() }
            val uniqueId = secureBuffer.readLong()

            secureBuffer.skipBytes(6)

            val password = secureBuffer.readString()
            secureBuffer.skipBytes(8)
            val randClient = secureBuffer.readLong()

            return LoginHeader(password, randClient, issacSeeds, uniqueId)
        }
    }
}