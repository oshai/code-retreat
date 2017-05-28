package functional2

import functional2.CState.*
import org.junit.Assert.assertEquals
import org.junit.Test

class GameOfLifeTest {

    val tested = GameOfLife()

    @Test
    fun `test num of alive neighbours`()  {
        val matrix = listOf(listOf(Dead, Alive, Dead),
                listOf(Dead, Alive, Dead),
                listOf(Dead, Alive, Dead))
        assertEquals(2, tested.calcLiveNeighbours(matrix, 1, 1))
    }
    @Test
    fun `test next state of cell - die of loneleyness`()  {
        val matrix = listOf(listOf(Dead, Dead, Dead),
                listOf(Dead, Alive, Dead),
                listOf(Dead, Alive, Dead))
        assertEquals(Dead, tested.calcNextState(matrix, 1, 1))
    }
}
