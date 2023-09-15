package org.rs3e.game.map

data class Tile(val x: Int, val plane: Int, val z: Int) {
    companion object {
        val ZERO = Tile(0, 0, 0)
    }
}