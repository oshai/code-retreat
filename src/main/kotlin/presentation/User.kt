package presentation

@SuppressWarnings("ALL")
class KotlinService {
    var name: String? = null

}

fun Service.appendToName(toAppend: () -> String): String {
    return "${this.name}-${toAppend()}"
}

fun main(args: Array<String>) {
    val service = Service()
    service.appendToName { "chidc2" }
    println("Name is ${service.name}")

    val world: String? = null
    println(world?.toUpperCase())
}

fun describe(obj: Any): String =
        when (obj) {
            is String -> "Not a string"
            in 1..10 -> "obj is in the range"
            else -> "Unknown"
        }




