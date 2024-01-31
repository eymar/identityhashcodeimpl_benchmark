package my.company.name

import kotlin.time.measureTime

fun main() {
    benchmark()
}

fun benchmark() {
    val listOf50k = (0..50_000).map {
        if (it % 5 == 0) {
            SomeClass1("String$it")
        } else if ( it % 3 == 0) {
            SomeClass2(it)
        } else if (it % 2 == 0){
            SomeClass3(it, "s$it")
        } else {
            Any()
        }
    }
    val set = IdentityArraySet<Any>()

    measureTime {
//            repeat(10) {
        repeat(1) {
            set.addAll(listOf50k)
            set.containsAll(listOf50k)
            listOf50k.forEach { set.remove(it) }
            require(set.isEmpty())
        }
    }.let {
        notify("Duration = ${it.inWholeMilliseconds}ms")
    }
}


expect fun notify(message: String)

class SomeClass1(val s: String)
class SomeClass2(val i: Int)
class SomeClass3(val i: Int, val s: String)