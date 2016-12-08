package tdd

import oshai.CellState

class Game(val world: List<List<CellState>>) {
    fun nextDay(): List<List<CellState>> {
        return world
    }

    fun  countLiveNeighbours(row: Int, col: Int): Int {
        var alive = 0
        for (i in Math.max(row-1, 0)..Math.min(row+1, world.size-1)){
            for (j in Math.max(col-1, 0)..Math.min(col+1, world.size-1)){
                if (world[i][j] == CellState.Alive &&
                !(row == i && col == j)) {
                    alive++
                }
            }
        }
        return alive
    }
}
