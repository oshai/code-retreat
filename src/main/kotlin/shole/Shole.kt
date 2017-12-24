package shole

import com.google.common.truth.Truth
import org.junit.Test

class Shole {
  fun calcNeighbours(matrix: List<List<Boolean>>): List<List<Int>> {
    return matrix.mapIndexed { i, list ->
      list.mapIndexed { j, isMine ->
        if (isMine) {
          -1
        }
        else {
          countNeighbours(i, j, matrix)
        }
      }
    }
  }

  private fun countNeighbours(i: Int, j: Int, matrix: List<List<Boolean>>): Int =
      ((i - 1).coerceAtLeast(0)..(i + 1).coerceAtMost(matrix.size - 1)).sumBy { iN ->
        ((j - 1).coerceAtLeast(0)..(j + 1).coerceAtMost(matrix[0].size - 1)).sumBy { jN ->
          if (matrix[iN][jN]) {
            1
          } else {
            0
          }
        }
      }

}

class SholeTest {
  private val tested = Shole()

  @Test
  fun `emptyBoard`() {
    Truth.assertThat(tested.calcNeighbours(listOf(listOf())))
        .isEqualTo(listOf(listOf<Int>()))
  }

  @Test
  fun `mine on board reports -1`() {
    Truth.assertThat(tested.calcNeighbours(listOf(listOf(true))))
        .isEqualTo(listOf(listOf(-1)))
  }

  @Test
  fun `mine on board reports -1 neighbors reports 1`() {
    Truth.assertThat(
        tested.calcNeighbours(
            listOf(
                listOf(true, false),
                listOf(false, false)
            )))
        .isEqualTo(
            listOf(
                listOf(-1, 1),
                listOf(1, 1)
            ))
  }

  @Test
  fun `complicated Board`() {
    Truth.assertThat(
        tested.calcNeighbours(
            listOf(
                listOf(true, false, false, false),
                listOf(false, false, true, false),
                listOf(false, false, false, false)
            )))
        .isEqualTo(
            listOf(
                listOf(-1, 2, 1, 1),
                listOf(1, 2, -1, 1),
                listOf(0, 1, 1, 1)
            ))
  }

  @Test
  fun `no mine on board reports 0 neighbors`() {
    Truth.assertThat(
        tested.calcNeighbours(
            listOf(
                listOf(false, false),
                listOf(false, false)
            )))
        .isEqualTo(
            listOf(
                listOf(0, 0),
                listOf(0, 0)
            ))
  }
}
