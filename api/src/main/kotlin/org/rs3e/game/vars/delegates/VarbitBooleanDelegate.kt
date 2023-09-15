package org.rs3e.game.vars.delegates

import org.rs3e.game.vars.VarDomain
import kotlin.reflect.KProperty

class VarbitBooleanDelegate<T>(private val id: Int, override val domain: VarDomain<T>) : VarDomainDelegate<T, Boolean> {
    override fun getValue(ref: T, prop: KProperty<*>): Boolean {
        if(domain.varbits.containsKey(id)) {
            val result = domain.varbits[id]!!
            if(result == 1) {
                return true
            }
        }
        return false
    }

    override fun setValue(ref: T, prop: KProperty<*>, value: Boolean) {
        val oldValue = domain.varbits[id] ?: 0
        if(oldValue != if(value) 1 else 0) {
            domain.varbits[id] = if(value) 1 else 0
            domain.notifyVarbitChange(id, oldValue, if(value) 1 else 0)
        }
    }
}