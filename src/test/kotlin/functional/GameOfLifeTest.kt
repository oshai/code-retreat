package functional

import functional.CellStateF.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test

class GameOfLifeTest {

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
        assertEquals(expected, result)
    }

    @Test fun `rule #2 remain alive with 2-3 neighbours`() {
        val matrix = listOf<List<CellStateF>>(
                listOf(Dead, Alive, Dead),
                listOf(Dead, Alive, Alive),
                listOf(Dead, Dead, Dead)
        )
        val expected = listOf<List<CellStateF>>(
                listOf(Dead, Alive, Alive),
                listOf(Dead, Alive, Alive),
                listOf(Dead, Dead, Dead)
        )
        val result = GameOfLife().calcNextState(matrix)
        assertEquals(expected, result)
    }
    @Test fun `rule #3 3 alive neighbours - reproduction`() {
        val matrix = listOf<List<CellStateF>>(
                listOf(Dead, Alive, Dead),
                listOf(Alive, Dead, Alive),
                listOf(Dead, Dead, Dead)
        )
        val expected = listOf<List<CellStateF>>(
                listOf(Dead, Alive, Dead),
                listOf(Dead, Alive, Dead),
                listOf(Dead, Dead, Dead)
        )
        val result = GameOfLife().calcNextState(matrix)
        assertEquals(expected, result)
    }
}

