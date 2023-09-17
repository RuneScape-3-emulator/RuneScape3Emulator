package org.rs3emulator.server.network.channel.handshake

import org.rs3emulator.server.network.channel.ResultType

data class HandshakeResponse(val handshake: HandshakeType, val result: ResultType)