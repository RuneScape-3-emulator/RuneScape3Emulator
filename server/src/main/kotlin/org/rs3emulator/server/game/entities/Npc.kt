package org.rs3emulator.server.game.entities

import org.rs3e.game.combat.CombatModel
import org.rs3e.game.entities.EntityType
import org.rs3e.game.entities.PathingEntity
import org.rs3e.game.map.Tile
import org.rs3e.game.vars.VarDomain
import org.rs3emulator.server.game.combat.LegacyModel

class Npc(override val id: Int) : PathingEntity {
    override val name: String
        get() = TODO("Not yet implemented")
    override val type: EntityType = EntityType.NPC
    override val tile: Tile = Tile.ZERO
    override val combatModel: CombatModel = LegacyModel(this)
    override val varDomain: VarDomain<out PathingEntity>
        get() = TODO("Not yet implemented")

    override fun onTick() {
        combatModel.onTick()
    }
}