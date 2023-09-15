package org.rs3e.game.vars.delegates

import org.rs3e.game.vars.VarDomain
import kotlin.reflect.KProperty

sealed interface VarDomainDelegate<T, R> {
    val domain: VarDomain<T>
    operator fun getValue(ref: T, prop: KProperty<*>): R
    operator fun setValue(ref: T, prop: KProperty<*>, value: R)
}