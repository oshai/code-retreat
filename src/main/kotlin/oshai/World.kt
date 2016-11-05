package oshai

import oshai.CellState.Alive
import oshai.CellState.Dead

class World(initialWorld: List<List<CellState>>) {
    private var worldState: List<List<CellState>> = initialWorld
    private val X_SIZE = initialWorld.size
    private val Y_SIZE = initialWorld.size

    fun tick(): Boolean {
        val newWorldState = cubeList(worldState.size) { x, y -> calcNewCell(x, y) }
        val isChangeNextDay = worldState != newWorldState
        worldState = newWorldState
        return isChangeNextDay
    }

    private fun calcNewCell(x: Int, y: Int): CellState {
        val cellState: CellState = worldState[x][y]
        val liveNeighbours: Int = calcLiveNeighbours(x, y)
        return when {
            cellState == Alive && liveNeighbours in 2..3 -> Alive
            cellState == Dead && liveNeighbours == 3 -> Alive
            else -> Dead
        }
    }

    private fun calcLiveNeighbours(x: Int, y: Int): Int {
        var live = 0
        for (i in Math.max(x - 1, 0)..Math.min(x + 1, X_SIZE - 1)) {
            for (j in Math.max(y - 1, 0)..Math.min(y + 1, Y_SIZE - 1)) {
                if (worldState[i][j] == Alive && !(i == x && j == y)) {
                    live += 1
                }
            }
        }
        return live
    }

    fun get(): List<List<CellState>> = worldState
}

