package oshai

import com.google.common.base.Joiner
import java.util.*

fun main(args: Array<String>) {
    Game.random(2).play()
}

class Game(world: List<List<CellState>>) {

    companion object {
        fun random(size: Int): Game {
            val random = Random()
            return Game(cubeList(size) { x, y -> CellState.values()[random.nextInt(2)] })
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

    private fun List<List<CellState>>.draw(round: Int) {
        this.forEach { lineArr -> println(Joiner.on(" ").join(lineArr.map { it.String })) }
        println("************* $round ************")
    }

    private fun delay() {
        Thread.sleep(DELAY)
    }

}

fun cubeList(size: Int, init: (Int, Int) -> CellState): List<List<CellState>> =
        (0..size-1).map {x -> (0..size-1).map{y -> init(x, y)}}
