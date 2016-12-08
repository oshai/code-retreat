package tdd

import org.junit.Assert.*
import org.junit.Test
import oshai.CellState

class GameTest {

    @Test fun `all dead cells then all remains dead`() {
        val world = listOf(listOf(CellState.Dead))
        val g = Game(world)
        assertEquals(world, g.nextDay())
    }
    @Test fun `one live cell then all becomes dead`() {
        val world = listOf(listOf(CellState.Dead, CellState.Dead, CellState.Dead),
                listOf(CellState.Dead, CellState.Alive, CellState.Dead),
                listOf(CellState.Dead, CellState.Dead, CellState.Dead))
        val g = Game(world)
        assertEquals(world, g.nextDay())
    }
    @Test fun `count number of neighbours in boundaries`() {
        val world = listOf(listOf(CellState.Dead))
        val g = Game(world)
        assertEquals(0, g.countLiveNeighbours(0, 0))
    }
    @Test fun `count number of neighbours for 2 neighbours`() {
        val world = listOf(listOf(CellState.Alive, CellState.Alive, CellState.Dead),
                listOf(CellState.Dead, CellState.Dead, CellState.Dead),
                listOf(CellState.Dead, CellState.Dead, CellState.Dead))
        val g = Game(world)
        assertEquals(2, g.countLiveNeighbours(1, 1))
    }
    @Test fun `count number of neighbours for 2 neighbours - should ignore self is alive`() {
        val world = listOf(listOf(CellState.Alive, CellState.Alive, CellState.Dead),
                listOf(CellState.Dead, CellState.Alive, CellState.Dead),
                listOf(CellState.Dead, CellState.Dead, CellState.Dead))
        val g = Game(world)
        assertEquals(2, g.countLiveNeighbours(1, 1))
    }
}
