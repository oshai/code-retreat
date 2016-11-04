package oshai

import org.junit.Assert.*
import org.junit.Test
import org.hamcrest.core.IsEqual.equalTo
import oshai.CellState.*

class WorldTest {

    @Test
    fun `test empty state`() {
        val state = listOf<List<CellState>>()
        val tested = World(state)
        val hasChanged = tested.tick()
        assertThat("change state in tick", hasChanged, equalTo(false))
        assertThat(tested.get(), equalTo(state))
    }

    @Test
    fun `test only dead values state`() {
        val state = listOf(
                listOf(Dead, Dead, Dead),
                listOf(Dead, Dead, Dead),
                listOf(Dead, Dead, Dead))
        val tested = World(state)
        val hasChanged = tested.tick()
        assertThat("change state in tick", hasChanged, equalTo(false))
        assertThat(tested.get(), equalTo(state))
    }

    @Test
    fun `test cell die of loneliness`() {
        val state = listOf(
                listOf(Dead, Dead, Dead),
                listOf(Dead, Alive, Dead),
                listOf(Dead, Dead, Dead))
        val tested = World(state)
        val hasChanged = tested.tick()
        assertThat("change state in tick", hasChanged, equalTo(true))
        assertThat(tested.get(), equalTo(listOf(
                listOf(Dead, Dead, Dead),
                listOf(Dead, Dead, Dead),
                listOf(Dead, Dead, Dead))))
    }

    @Test
    fun `test cell remain alive with 3 live neighbours`() {
        val state = listOf(
                listOf(Alive, Alive),
                listOf(Alive, Alive))
        val tested = World(state)
        val hasChanged = tested.tick()
        assertThat("change state in tick", hasChanged, equalTo(false))
        assertThat(tested.get(), equalTo(listOf(
                listOf(Alive, Alive),
                listOf(Alive, Alive))
        ))
    }

    @Test
    fun `test cell dies of over population with 4 alive neighbours`() {
        val state = listOf(
                listOf(Dead, Dead, Alive),
                listOf(Alive, Alive, Alive),
                listOf(Alive, Dead, Dead))
        val tested = World(state)
        val hasChanged = tested.tick()
        assertThat("change state in tick", hasChanged, equalTo(true))
        assertThat(tested.get(), equalTo(listOf(
                listOf(Dead, Dead, Alive),
                listOf(Alive, Dead, Alive),
                listOf(Alive, Dead, Dead))
        ))
    }

    @Test
    fun `test cell reproduces when dead with 3 live neighbours`() {
        val state = listOf(
                listOf(Dead, Alive),
                listOf(Alive, Alive))
        val tested = World(state)
        val hasChanged = tested.tick()
        assertThat("change state in tick", hasChanged, equalTo(true))
        assertThat(tested.get(), equalTo(listOf(
                listOf(Alive, Alive),
                listOf(Alive, Alive))
        ))
    }
}
