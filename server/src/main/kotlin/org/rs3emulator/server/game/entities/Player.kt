package org.rs3emulator.server.game.entities

import org.rs3e.game.combat.CombatModel
import org.rs3e.game.entities.EntityType
import org.rs3e.game.entities.PathingEntity
import org.rs3e.game.map.Tile
import org.rs3emulator.server.game.combat.LegacyModel

class Player(override val id: Int, override val name: String) : PathingEntity {
    override val type: EntityType = EntityType.PLAYER
    override val tile: Tile = Tile.ZERO
    override val combatModel: CombatModel = LegacyModel(this)

    override fun onTick() {
        combatModel.onTick()
    }
}