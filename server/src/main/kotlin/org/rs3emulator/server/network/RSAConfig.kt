package org.rs3emulator.server.network

import java.math.BigInteger

data class RSAConfig(val exponent: BigInteger, val modulus: BigInteger) {

    fun cryptRSA(data: ByteArray): ByteArray? {
        return BigInteger(data).modPow(exponent, modulus).toByteArray()
    }

}