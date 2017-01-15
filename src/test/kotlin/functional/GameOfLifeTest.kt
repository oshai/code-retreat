package functional

import functional.CellStateF.*
import org.junit.Assert.assertEquals
import org.junit.Test

class GameOfLifeTest {

    @Test fun `check number of live neigbours for empty matrix`() {
        val matrix = listOf<List<CellStateF>>(
                listOf(Dead, Dead, Dead),
                listOf(Dead, Dead, Dead),
                listOf(Dead, Dead, Dead)
        )
        val result = GameOfLife().countLiveNeighbours(matrix, 1, 1)
        assertEquals(0, result);
    }

    @Test fun `check number of live neigbours middle cell`() {
        val matrix = listOf<List<CellStateF>>(
                listOf(Dead, Alive, Dead),
                listOf(Dead, Alive, Dead),
                listOf(Dead, Alive, Dead)
        )
        val result = GameOfLife().countLiveNeighbours(matrix, 1, 1)
        assertEquals(2, result);
    }

    @Test fun `rule #1 die of soiltude`() {
        val matrix = listOf<List<CellStateF>>(
                listOf(Dead, Dead, Dead),
                listOf(Dead, Alive, Dead),
                listOf(Dead, Dead, Dead)
        )
        val expected = listOf<List<CellStateF>>(
                listOf(Dead, Dead, Dead),
                listOf(Dead, Dead, Dead),
                listOf(Dead, Dead, Dead)
        )
        val result = GameOfLife().calcNextState(matrix)
        assertEquals(expected, result);
    }

    @Test fun `rule #2 remain alive with 2-3 neighbours`() {
        val matrix = listOf<List<CellStateF>>(
                listOf(Dead, Alive, Dead),
                listOf(Dead, Alive, Alive),
                listOf(Dead, Dead, Dead)
        )
        val expected = listOf<List<CellStateF>>(
                listOf(Dead, Alive, Dead),
                listOf(Dead, Alive, Alive),
                listOf(Dead, Dead, Dead)
        )
        val result = GameOfLife().calcNextState(matrix)
        assertEquals(expected, result);
    }
    @Test fun `rule #3 3 alive neighbours - reproduction`() {
        val matrix = listOf<List<CellStateF>>(
                listOf(Dead, Alive, Dead),
                listOf(Dead, Alive, Alive),
                listOf(Dead, Dead, Dead)
        )
        val expected = listOf<List<CellStateF>>(
                listOf(Dead, Alive, Dead),
                listOf(Dead, Alive, Alive),
                listOf(Dead, Dead, Dead)
        )
        val result = GameOfLife().calcNextState(matrix)
        assertEquals(expected, result);
    }
}

