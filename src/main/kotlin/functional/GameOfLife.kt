package functional

import functional.CellStateF.*


class GameOfLife {
    fun countLiveNeighbours(matrix: List<List<CellStateF>>, i: Int, j: Int): Int {
        return matrix.mapIndexed { i1, list1 ->
            list1.mapIndexed { j1, cellStateF ->
                i1 in i - 1..i + 1 && j1 in j - 1..j + 1
                        && !(i1 == i && j1 == j)
                        && cellStateF == Alive
            }
        }.sumBy { list -> list.sumBy { bool -> if (bool) 1 else 0 } }
    }

    fun calcNextState(matrix: List<List<CellStateF>>): List<List<CellStateF>> {
        return matrix.mapIndexed { i, list -> list.mapIndexed { j, currentCellState ->
            val numOfLiveNeighbours = countLiveNeighbours(matrix, i, j)
            if (currentCellState == Dead) Dead else
                when (numOfLiveNeighbours) {
                    in 0..1 -> Dead
                    in 2..3 -> Alive
                    else -> Dead
                }
        } }
    }
}

enum class CellStateF {
    Dead, Alive
}


