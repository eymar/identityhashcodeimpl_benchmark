package my.company.name

import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.time.measureTime

class Benchmarks {

    /**
     * jvm (System.identityHashCode): 4901ms
     * wasm (default instance.hashCode): 3851-4050ms ... 4200ms (when adding different types to the list)
     * wasm (instance._hashCode): ~3780-4100ms
     * wasm (weakMap_jsInterop): ~4956ms
     */
    @Test
    fun runBenchmark() {
        benchmark()
    }
}
