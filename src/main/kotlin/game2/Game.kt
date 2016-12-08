package game2

import game2.CellState.*
import java.util.*

class Game {
    var currentWorld: List<List<CellState>> = initWorld()

    private fun  initWorld(): List<List<CellState>> {
        return (1..20).toList().map { (1..20).toList().map { if (Random().nextBoolean()) populate else unpopulate } }
    }

    var round = 0
    fun play() {
        while (true) {
            val nextWorld: List<List<CellState>> = calcNext()
            currentWorld = nextWorld
            println("================ $round")
            round++
            Thread.sleep(500)
            printBoard(nextWorld)
        }
    }

    private fun printBoard(world: List<List<CellState>>) {
        for (i in 0..currentWorld.size-1) {
            for (j in 0..currentWorld[i].size-1) {
                print(if (currentWorld[i][j] == populate) "X " else "- ")
            }
            println()
        }

    }

    private fun  calcNext(): List<List<CellState>> {
        val nextWorld = currentWorld.mapIndexed { i, list ->  list.mapIndexed { j, cellState ->
            calcNextCell(i, j)
        }}
        return nextWorld
    }

    private fun  calcNextCell(i: Int, j: Int): CellState {
        val neighbours: Int = calcLiveNeighbours(i, j)
        return when (currentWorld[i][j]) {
            populate -> when(neighbours) {
                in 2..3 -> populate
                else -> unpopulate
            }
            unpopulate -> when (neighbours) {
                3 -> populate
                else -> unpopulate
            }
        }
    }

    private fun  calcLiveNeighbours(i: Int, j: Int): Int {
        var live = 0
        for (k in i-1..i+1) {
            for (l in j-1..j+1) {
                if (!(k == i && l == j) &&
                    k >= 0 && k <= currentWorld.size - 1 &&
                    l >= 0 && l <= currentWorld.size - 1 &&
                currentWorld[k][l] == populate) {
                    live += 1
                }
            }
        }
        return live
    }
}

fun main(args: Array<String>) {
    Game().play()
}

enum class CellState {
    populate,
    unpopulate
}

