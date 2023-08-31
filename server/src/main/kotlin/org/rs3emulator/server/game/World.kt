package org.rs3emulator.server.game

class World : Runnable {

    val TICK_LENGTH_MS: Long = 600

    override fun run() {
        var lastTickTime = System.currentTimeMillis()

        // Main server loop
        while (true) {
            val currentTime = System.currentTimeMillis()
            val deltaTime = currentTime - lastTickTime
            if (deltaTime >= TICK_LENGTH_MS) {
                // Perform game update
                // TODO implement game tick
                lastTickTime = currentTime
            } else {
                // Sleep the thread if there's time left in this tick
                try {
                    val time = TICK_LENGTH_MS - deltaTime
                    if (time > 0) {
                        Thread.sleep(time)
                    }
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
    }
}