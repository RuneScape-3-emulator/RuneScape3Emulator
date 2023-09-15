package org.rs3e.game.combat

import org.rs3e.game.entities.PathingEntity

interface CombatModel {

    val me: PathingEntity
    val mechanics: CombatMechanics
    val attributes: Map<String, Int>

    fun initiate(target: PathingEntity)
    fun onTick()

    fun alternate(mechanic: CombatMechanics)

}