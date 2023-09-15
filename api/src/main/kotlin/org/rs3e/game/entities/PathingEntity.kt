package org.rs3e.game.entities

import org.rs3e.game.combat.CombatModel

interface PathingEntity : Entity {

    val name: String
    val combatModel: CombatModel

}