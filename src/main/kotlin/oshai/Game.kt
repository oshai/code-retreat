package oshai

import com.google.common.base.Joiner
import java.util.*

fun main(args: Array<String>) {
    Game.random(15).play()
}

class Game(world: Array<Array<CellState>>) {

    companion object {
        fun random(size: Int): Game {
            val random = Random()
            return Game(cubeArray2d(size) { x, y -> CellState.values()[random.nextInt(2)] })
        }
    }

    fun play() {
        var round = 0
        var isNoChangeNextDay = false
        while (!isNoChangeNextDay) {
            world.get().draw(round)
            isNoChangeNextDay = world.tick()
            round++
            delay()
        }
        println("GAME OVER!")
    }

    private val DELAY = 500L
    private val world = World(world)

    private fun Array<Array<CellState>>.draw(round: Int) {
        this.forEach { lineArr -> println(Joiner.on(" ").join(lineArr.map { it.String })) }
        println("************* $round ************")
    }

    private fun delay() {
        Thread.sleep(DELAY)
    }

}

inline fun <reified INNER> cubeArray2d(size: Int, noinline innerInit: (Int, Int) -> INNER): Array<Array<INNER>>
        = Array(size) { outerCoord -> Array<INNER>(size) { innerCoord -> innerInit(outerCoord, innerCoord) } }
