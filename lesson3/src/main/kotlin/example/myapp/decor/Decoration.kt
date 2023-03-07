package example.myapp.decor

data class Decoration(val rocks: String) {

}
data class Decoration2(val rocks: String, val wood: String, val diver: String) {
}

class Choice {
    companion object {
        var name: String = "lyric"
        fun showDescription(name: String) = println("My favorite $name")
    }
}

fun makeDecorations() {
    val decoration1 = Decoration("granite")
    println(decoration1)

    val decoration2 = Decoration("slate")
    println(decoration2)

    val decoration3 = Decoration("slate")
    println(decoration3)

    println (decoration1.equals(decoration2))
    println (decoration3.equals(decoration2))

    val decoration5 = Decoration2("crystal", "wood", "diver")
    println(decoration5)
    val (rock, wood, diver) = decoration5
    println(rock)
    println(wood)
    println(diver)
}

fun main() {
//    makeDecorations()
    println(Choice.name)
    Choice.showDescription("pick")
    Choice.showDescription("selection")
}