package org.rs3e.game.entities

import org.rs3e.game.map.Tile

interface Entity {
    val id: Int
    val type: EntityType
    val tile: Tile

    fun onTick()
}