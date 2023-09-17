package org.rs3emulator.server.utilities

import io.netty.buffer.ByteBuf

object ByteBufExtensions {

    fun ByteBuf.readString(): String {
        val strBuilder = StringBuilder()
        while (isReadable) {
            val byteValue = readByte()
            if (byteValue.toInt() == 0) {
                break
            }
            strBuilder.append(byteValue.toChar())
        }
        return strBuilder.toString()
    }

    fun ByteBuf.decipherXtea(keys: IntArray, start: Int = readerIndex(), end: Int = readerIndex() + readableBytes()) {
        val readerIndex = readerIndex()
        val writerIndex = writerIndex()

        readerIndex(start)
        val i1 = (end - start) / 8
        for (j1 in 0 until i1) {
            var k1 = readInt()
            var l1 = readInt()
            var sum = -0x3910c8e0
            val delta = -0x61c88647
            var k2 = 32
            while (k2-- > 0) {
                l1 -= keys[(sum and 0x1c84).ushr(11)] + sum xor (k1.ushr(5) xor (k1 shl 4)) + k1
                sum -= delta
                k1 -= (l1.ushr(5) xor (l1 shl 4)) + l1 xor keys[sum and 3] + sum
            }

            val oldReaderIndex = readerIndex()
            readerIndex(0)

            writerIndex(oldReaderIndex - 8)
            writeInt(k1)
            writeInt(l1)

            readerIndex(oldReaderIndex)
            writerIndex(writerIndex)
        }

        writerIndex(writerIndex)
        readerIndex(readerIndex)
    }

}