package org.rs3e.game.vars.delegates

import org.rs3e.game.vars.VarDomain
import kotlin.reflect.KProperty

class VarDelegate<T>(private val id: Int, override val domain: VarDomain<T>) : VarDomainDelegate<T, Int> {
    override fun getValue(ref: T, prop: KProperty<*>): Int {
        return domain.vars[id] ?: 0
    }

    override fun setValue(ref: T, prop: KProperty<*>, value: Int) {
        val oldValue = domain.vars[id] ?: 0
        if(oldValue != value) {
            domain.vars[id] = value
            domain.notifyVarChange(id, oldValue, value)
        }
    }
}