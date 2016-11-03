package oshai

import com.google.common.base.Joiner
import java.util.*

fun main(args: Array<String>) {
    val random = Random()
    val array = array2d(Game.X_SIZE, Game.Y_SIZE) {x,y -> CellState.values()[random.nextInt(2)]}
    Game(array).play()

}

class Game(initialWorld: Array<Array<CellState>>) {

    private var world: Array<Array<CellState>> = initialWorld
    private var i = 0
    private var static = false

    companion object {
        val X_SIZE: Int = 12
        val Y_SIZE: Int = 12
    }

    private fun Array<Array<CellState>>.draw() {
        this.forEach { lineArr -> println(Joiner.on(" ").join(lineArr.map { it.String })) }
        println("************* $i ************")
    }

    private fun clear() {
        Thread.sleep(1000)
    }

    fun play() {
        while (!static) {
            world.draw()
            clear()
            calcNextDay()
            i++
        }
        world.draw()
        println("GAME OVER!")
    }

    private fun calcNextDay() {
        val newWorld = array2d(Game.X_SIZE, Game.Y_SIZE ) {x,y -> calcNewCell(x,y)}
        static = Arrays.deepEquals(world,newWorld)
        world = newWorld
    }

    private fun  calcNewCell(x: Int, y: Int): CellState {
        val currentCellState: CellState = world[x][y]
        val liveNeighbours: Int = calcLiveNeighbours(x, y)
        return when {
            currentCellState == CellState.Alive && liveNeighbours < 2 -> CellState.Dead
            currentCellState == CellState.Alive && liveNeighbours >= 2 && liveNeighbours <= 3 -> CellState.Alive
            currentCellState == CellState.Alive && liveNeighbours > 3 -> CellState.Dead
            currentCellState == CellState.Dead && liveNeighbours == 3 -> CellState.Alive
            else -> CellState.Dead
        }
    }

    private fun calcLiveNeighbours(x: Int, y: Int): Int {
        var live = 0
        for (i in Math.max(x-1, 0)..Math.min(x+1, X_SIZE - 1)) {
            for (j in Math.max(y-1, 0)..Math.min(y+1, Y_SIZE - 1)) {
                if (world[i][j] == CellState.Alive && !(i == x && j == y)) {
                    live += 1
                }
            }
        }
        return live
    }
}

enum class CellState(val String: String) {
    Dead("-"),
    Alive("X")
}

inline fun <reified INNER> array2d(sizeOuter: Int, sizeInner: Int, noinline innerInit: (Int, Int) -> INNER): Array<Array<INNER>>
        = Array(sizeOuter) { outerCoord -> Array<INNER>(sizeInner) { innerCoord -> innerInit(outerCoord, innerCoord)} }