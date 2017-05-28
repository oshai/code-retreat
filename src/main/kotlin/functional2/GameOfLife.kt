package functional2

class GameOfLife {
    fun calcLiveNeighbours(matrix: List<List<CState>>, i: Int, j: Int): Int {
        return matrix.mapIndexed { i1, list ->
            list.filterIndexed { j1, cState ->
                (i1 in (i - 1..i + 1) && (j1 in (j - 1..j + 1)))
                && cState == CState.Alive
                && !(i1 == i && j1 == j)
            }.count()
        }.sum()
    }

    fun  calcNextState(matrix: List<List<CState>>, i: Int, j: Int): CState {
        val liveNeighbours = calcLiveNeighbours(matrix, i, j)
        return when (liveNeighbours) {
            else -> CState.Dead
        }
    }
}

enum class CState {
    Dead,
    Alive
}
