package nomouse2

class GameOfMouse {
    fun countNeighbours(board: List<List<MouseState>>, i: Int, j: Int): Int =
            ((i - 1).coerceAtLeast(0)..(i + 1).coerceAtMost(board.size-1))
                    .map { i1 ->
                        ((j - 1).coerceAtLeast(0)..(j + 1).coerceAtMost(board.size-1))
                                .map { j1 ->
                                    if (i == i1 && j == j1) {
                                        0
                                    }
                                    else {
                                        board[i1][j1].toValue()
                                    }
                                }.sum()
                    }.sum()
}

private fun MouseState.toValue(): Int = if (this == MouseState.Alive) 1 else 0

enum class MouseState {
    Dead, Alive
}
