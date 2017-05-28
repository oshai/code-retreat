package nomouse

import org.junit.Assert.assertEquals
import org.junit.Test

class GameOfMouseTest{
    @Test fun `count neighbours`() {
        val board = listOf(
                listOf(MouseCell.Alive, MouseCell.Dead, MouseCell.Dead),
                listOf(MouseCell.Dead, MouseCell.Alive, MouseCell.Dead),
                listOf(MouseCell.Alive, MouseCell.Dead, MouseCell.Dead))
        val neighbours = GameOfMouse().countNeighbours(board,
                0, 0)
        assertEquals(1, neighbours)
    }

    @Test fun `calc cell state - remains alive`(){
        val board = listOf(
                listOf(MouseCell.Dead, MouseCell.Dead, MouseCell.Dead),
                listOf(MouseCell.Dead, MouseCell.Alive, MouseCell.Dead),
                listOf(MouseCell.Alive, MouseCell.Dead, MouseCell.Dead))
        val state = GameOfMouse().calcState(board, 1,1)
        assertEquals(MouseCell.Dead, state)
    }

}

