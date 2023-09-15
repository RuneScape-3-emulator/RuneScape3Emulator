package org.rs3e.game.entities

import org.rs3e.game.combat.CombatModel
import org.rs3e.game.vars.VarDomain

interface PathingEntity : Entity {

    val name: String
    val combatModel: CombatModel
    val varDomain: VarDomain<out PathingEntity>

}