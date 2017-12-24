package presentation

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.runBlocking

data class Campaign(val id: Int, val name: String)

fun main(args: Array<String>): Unit =
        runBlocking {
            val jobs = List(100_000) { index ->
                async(CommonPool) {
                    delay(1000L)
                    index
                }
            }

            println(
                    jobs.sumBy { it.await() }
            )
        }

//    val fruits = listOf("banana", "avocado", "apple", "kiwi")
//    fruits
//            .filter { it.startsWith("a") }
//            .sortedBy { it }
//            .map { it.toUpperCase() }
//            .forEach { println(it) }
//}
