package functional3

import functional3.CState.*
import org.junit.Assert.assertEquals
import org.junit.Test

class GameOfLifeTest {

    @Test fun `next state when all empty`() {
        val tested = GameOfLife()
        val nextState: CState = tested.getNextState(
                listOf(
                        listOf(Dead, Dead, Dead),
                        listOf(Dead, Alive, Dead),
                        listOf(Dead, Dead, Dead)
                ), 1, 1)
        assertEquals(Dead, nextState)
    }
    @Test fun `next state when all remain alive`() {
        val tested = GameOfLife()
        val nextState: CState = tested.getNextState(
                listOf(
                        listOf(Dead, Dead, Alive),
                        listOf(Dead, Alive, Alive),
                        listOf(Dead, Dead, Dead)
                ), 1, 1)
        assertEquals(Alive, nextState)
    }
    @Test fun `next state reproduction`() {
        val tested = GameOfLife()
        val nextState: CState = tested.getNextState(
                listOf(
                        listOf(Dead, Dead, Alive),
                        listOf(Dead, Dead, Alive),
                        listOf(Dead, Dead, Alive)
                ), 1, 1)
        assertEquals(Alive, nextState)
    }
    @Test fun `next state when more than 3 alive will be dead`() {
        val tested = GameOfLife()
        val nextState: CState = tested.getNextState(
                listOf(
                        listOf(Dead, Dead, Alive),
                        listOf(Dead, Alive, Alive),
                        listOf(Dead, Alive, Alive)
                ), 1, 1)
        assertEquals(Dead, nextState)
    }

    @Test fun `count number of live neighbours`() {
        val tested = GameOfLife()
        val live: Int = tested.getNumOfLiveNeighbours(
                listOf(
                        listOf(Dead, Dead, Alive),
                        listOf(Dead, Alive, Dead),
                        listOf(Dead, Dead, Dead)
                ), 1, 1)
        assertEquals(1, live)
    }

    @Test fun `count number of live neighbours for corner`() {
        val tested = GameOfLife()
        val live: Int = tested.getNumOfLiveNeighbours(
                listOf(
                        listOf(Dead, Dead, Dead),
                        listOf(Dead, Alive, Dead),
                        listOf(Dead, Dead, Dead)
                ), 0, 0)
        assertEquals(1, live)
    }

    @Test fun `count number of live neighbours for right corner`() {
        val tested = GameOfLife()
        val live: Int = tested.getNumOfLiveNeighbours(
                listOf(
                        listOf(Dead, Dead, Dead),
                        listOf(Dead, Alive, Dead),
                        listOf(Dead, Dead, Dead)
                ), 2, 2)
        assertEquals(1, live)
    }
}

enum class CState {Dead, Alive }

class GameOfLife {


    fun getNextState(board: List<List<CState>>): List<List<CState>> {
        return board.mapIndexed { i, list -> list.mapIndexed { j, cState ->
            getNextState(board, i, j)
        } }
    }

    fun getNextState(board: List<List<CState>>, i: Int, j: Int): CState {
        val numOfLiveNeighbours = getNumOfLiveNeighbours(board, i, j)
        return when (board[i][j]) {
            Dead -> if (numOfLiveNeighbours == 3) Alive else Dead
            Alive -> when (numOfLiveNeighbours) {
                in 2..3 -> Alive
                else -> Dead
            }
        }
    }

    fun getNumOfLiveNeighbours(board: List<List<CState>>, i: Int, j: Int): Int {
        val result = ((i - 1).coerceAtLeast(0)..((i + 1).coerceAtMost(board.size - 1))).map { i1 ->
            ((j - 1).coerceAtLeast(0)..(j + 1).coerceAtMost(board.size - 1)).map { j1 ->
                if (i1 == i && j1 == j) {
                    0
                } else when (board[i1][j1]) {
                    Dead -> 0
                    Alive -> 1
                }
            }
        }
        return result.sumBy { it.sum() }
    }

}
