package nomouse2

import org.junit.Assert.assertEquals
import org.junit.Test

class GameOfMouseTest {

    @Test fun `test count neighbours`(){
        val board = listOf(
                listOf(MouseState.Dead, MouseState.Alive, MouseState.Dead),
                listOf(MouseState.Dead, MouseState.Dead, MouseState.Dead),
                listOf(MouseState.Dead, MouseState.Dead, MouseState.Dead)
        )
        val tested = GameOfMouse()
        val result = tested.countNeighbours(board, 1, 1)
        assertEquals(1, result)
    }
    @Test fun `test count neighbours ignore self`(){
        val board = listOf(
                listOf(MouseState.Dead, MouseState.Alive, MouseState.Dead),
                listOf(MouseState.Dead, MouseState.Alive, MouseState.Dead),
                listOf(MouseState.Dead, MouseState.Dead, MouseState.Dead)
        )
        val tested = GameOfMouse()
        val result = tested.countNeighbours(board, 1, 1)
        assertEquals(1, result)
    }
    @Test fun `test count neighbours corner case`(){
        val board = listOf(
                listOf(MouseState.Dead, MouseState.Alive, MouseState.Dead),
                listOf(MouseState.Dead, MouseState.Alive, MouseState.Dead),
                listOf(MouseState.Dead, MouseState.Dead, MouseState.Dead)
        )
        val tested = GameOfMouse()
        val result = tested.countNeighbours(board, 0, 0)
        assertEquals(2, result)
    }
}
