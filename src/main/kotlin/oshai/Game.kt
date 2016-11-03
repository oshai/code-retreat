package oshai

import com.google.common.base.Joiner
import java.util.*

fun main(args: Array<String>) {
    val random = Random()
    val array = array2d(Game.X_SIZE, Game.Y_SIZE) {x,y -> CellState.values()[Math.max(random.nextInt(6) - 4, 0)]}
    Game(array).play()

}

class Game(initialWorld: Array<Array<CellState>>) {

    private var world: Array<Array<CellState>> = initialWorld

    companion object {
        val X_SIZE: Int = 5
        val Y_SIZE: Int = 5
    }

    private fun Array<Array<CellState>>.draw() {
        this.forEach { lineArr -> println(Joiner.on(" ").join(lineArr.map { it.String })) }
        println("----------")
    }

    private fun clear() {
        Thread.sleep(1000)
    }

    fun play() {
        while (!gameOver()) {
            world.draw()
            clear()
            calcNextDay()
        }
    }

    private fun calcNextDay() {
        val newWorld = array2d(Game.X_SIZE, Game.Y_SIZE ) {x,y -> calcNewCell(x,y)}
    }

    private fun  calcNewCell(x: Int, y: Int): CellState {
    }

    private fun  gameOver(): Boolean = world.all { it.all { it == CellState.Dead } }
}

enum class CellState(val String: String) {
    Dead("O"),
    Alive("X")
}

inline fun <reified INNER> array2d(sizeOuter: Int, sizeInner: Int, noinline innerInit: (Int, Int) -> INNER): Array<Array<INNER>>
        = Array(sizeOuter) { outerCoord -> Array<INNER>(sizeInner) { innerCoord -> innerInit(outerCoord, innerCoord)} }