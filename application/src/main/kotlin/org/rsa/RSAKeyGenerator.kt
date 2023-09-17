package org.rsa

import org.rs3emulator.server.utilities.RSAUtil
import java.math.BigInteger
import java.nio.file.Files
import java.nio.file.Paths

object RSAKeyGenerator {

    @JvmStatic
    fun main(args: Array<String>) {

        /*val spec = RSAUtil.generateKeySpec(1024)

        println(spec.privateExponent.toString())
        println()
        println(spec.modulus.toString())*/

        val bb = BigInteger("96172667988861366794965247690639670970473692773291394360146774360091619423143215756627153689800771595104688836841231414167910997232987452300156365811111048012120562136211018813048258776889356108685839908591821176520356483726373227386287798546403102723438349960502736005023413114477489679088178624450797368749")

        println(bb.toString(0x10))



        /*val key = RSAUtil.findRSAKey(Files.readAllBytes(Paths.get("C:\\ProgramData\\Jagex\\launcher").resolve("rs2client.exe")), 1024)

        println("Key @ ${key?.toString(16)}")*/

    }

}