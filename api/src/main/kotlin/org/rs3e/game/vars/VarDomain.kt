package org.rs3e.game.vars

import org.rs3e.game.vars.delegates.VarDelegate
import org.rs3e.game.vars.delegates.VarbitBooleanDelegate
import org.rs3e.game.vars.delegates.VarbitDelegate

interface VarDomain<T> {

    val vars: MutableMap<Int, Int>
    val varbits: MutableMap<Int, Int>

    fun notifyVarChange(id: Int, oldValue: Int, newValue: Int)
    fun notifyVarbitChange(id: Int, oldValue: Int, newValue: Int)

    companion object {
        fun <T> VarDomain<T>.varbit(id: Int) = VarbitDelegate(id, this)
        fun <T> VarDomain<T>.varbool(id: Int) = VarbitBooleanDelegate(id, this)
        fun <T> VarDomain<T>.variable(id: Int) = VarDelegate(id, this)
    }

}