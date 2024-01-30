package my.company.name

import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.time.measureTime

class Benchmarks {

    /**
     * jvm (System.identityHashCode): 4901ms
     * wasm (default instance.hashCode): 3851-4050ms
     * wasm (instance._hashCode): ~3780-4100ms
     * wasm (weakMap_jsInterop): ~4956ms
     */
    @Test
    fun benchmark() {
        val listOf10k = (0..100_000).map { Any() }
        val set = IdentityArraySet<Any>()

        measureTime {
            repeat(10) {
//            repeat(1) {
                set.addAll(listOf10k)
                set.containsAll(listOf10k)
                listOf10k.forEach { set.remove(it) }
                assertTrue(set.isEmpty())
            }
        }.let {
            println("Duration = ${it.inWholeMilliseconds}ms")
        }
    }
}