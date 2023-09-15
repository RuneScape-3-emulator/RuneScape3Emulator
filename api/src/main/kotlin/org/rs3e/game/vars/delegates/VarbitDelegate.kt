package org.rs3e.game.vars.delegates

import org.rs3e.game.vars.VarDomain
import kotlin.reflect.KProperty

class VarbitDelegate<T>(private val id: Int, override val domain: VarDomain<T>) : VarDomainDelegate<T> {
    override fun getValue(ref: T, prop: KProperty<*>): Int {
        return domain.varbits[id] ?: 0
    }

    override fun setValue(ref: T, prop: KProperty<*>, value: Int) {
        val oldValue = domain.varbits[id] ?: 0
        if(oldValue != value) {
            domain.varbits[id] = value
            domain.notifyVarbitChange(id, oldValue, value)
        }
    }

    companion object {
        fun <T> VarbitDelegate<T>.asBoolean() {

        }
    }
}