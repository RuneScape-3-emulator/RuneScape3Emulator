package org.rs3e.game.combat

import org.rs3e.game.entities.PathingEntity

interface CombatMechanics {

    fun onTick(model: CombatModel, attacker: PathingEntity, target: PathingEntity)

}