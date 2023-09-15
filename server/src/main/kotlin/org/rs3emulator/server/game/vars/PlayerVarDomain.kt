package org.rs3emulator.server.game.vars

import org.rs3e.game.vars.VarDomain
import org.rs3emulator.server.game.entities.Player

class PlayerVarDomain(val player: Player) : VarDomain<Player> {
    override val varbits: MutableMap<Int, Int> = mutableMapOf()
    override val vars: MutableMap<Int, Int> = mutableMapOf()

    override fun notifyVarChange(id: Int, oldValue: Int, newValue: Int) {
        TODO("Send var update packet")
    }
    override fun notifyVarbitChange(id: Int, oldValue: Int, newValue: Int) {
        TODO("Send varbit update packet")
    }
}