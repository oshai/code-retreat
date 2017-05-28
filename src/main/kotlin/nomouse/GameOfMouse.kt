package nomouse

import nomouse.MouseCell.*


class GameOfMouse {
    fun countNeighbours(board: List<List<MouseCell>>, i: Int, j: Int): Int {
        return ((i-1).coerceAtLeast(0)..(i+1).coerceAtMost(board.size-1)).map {
            i1 -> ((j-1).coerceAtLeast(0)..(j+1).coerceAtMost(board.size-1)).map { j1 ->
            if (board[i1][j1] == Alive && !(i == i1 && j == j1)) 1 else 0
        } }.sumBy(List<Int>::sum)
    }

    fun calcState(board: List<List<MouseCell>>, i: Int, j: Int): MouseCell {
        val countNeighbours = countNeighbours(board, i, j)
        return when (board[i][j]) {
            Dead -> if (countNeighbours == 3) Alive else Dead
            Alive ->
            when (countNeighbours) {
                in 2..3 -> Alive
                else -> Dead
            }
        }
    }
}

enum class MouseCell {
    Dead,
    Alive
}
