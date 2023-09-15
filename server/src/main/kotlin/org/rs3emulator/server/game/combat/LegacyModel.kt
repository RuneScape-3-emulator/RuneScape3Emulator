package org.rs3emulator.server.game.combat

import org.rs3e.game.combat.CombatMechanics
import org.rs3e.game.combat.CombatModel
import org.rs3e.game.entities.PathingEntity

class LegacyModel(override val me : PathingEntity) : CombatModel {

    private val _combatAttributes = mutableMapOf<String, Int>()
    private var target: PathingEntity? = null
    private var _mechanics: CombatMechanics = TODO("Write default mechanics")

    override val mechanics: CombatMechanics
        get() = _mechanics
    override val attributes: Map<String, Int>
        get() = _combatAttributes

    override fun initiate(target: PathingEntity) {
        this.target = target
    }


    override fun onTick() {
        //TODO("Adjust combat attributes for legacy combat")
        if (this.target != null && this.me != this.target) {
            _mechanics.onTick(this, this.me, this.target!!)
        }
    }

    override fun alternate(mechanic: CombatMechanics) {
        _mechanics = mechanics
    }
}