package my.company.name

import kotlin.random.Random

actual fun identityHashCode(instance: Any?): Int {
//    return usingInstanceHashCode(instance)
    return usingInternal_HashCodeRandom(instance)
//    return usingWeakMapJsInterop(instance)
//    return instance.hashCodeExtensionFromKotlin()
}

inline fun usingInstanceHashCode(instance: Any?): Int {
    if (instance == null) {
        return 0
    }
    return instance.hashCode()
}

inline fun usingInternal_HashCodeRandom(instance: Any?): Int {
    if (instance == null) {
        return 0
    }
    return instance.identityHashCode()
}

// !!! using this is around 8 times slower!!! Duration = 3173ms vs 389ms for 1 iteration
// copied from kotlin Any?.hashCode(): Int
inline fun Any?.hashCodeExtensionFromKotlin(): Int = this?.hashCode() ?: 0

@Suppress("INVISIBLE_MEMBER")
fun Any.identityHashCode(): Int {
    if (_hashCode == 0)
        _hashCode = Random.nextInt(Int.MIN_VALUE, Int.MAX_VALUE)
    return _hashCode
}

external class WeakMap<K: JsAny> {
    fun set(key: K, value: Int)
    fun has(key: K): Boolean
    fun get(key: K): Int
}

val weakMap = WeakMap<JsReference<Any>>()
var nextHashCode = 1

inline fun usingWeakMapJsInterop(instance: Any?): Int {
    if (instance == null) {
        return 0
    }

    val jsRef = instance.toJsReference()
    val has = weakMap.has(jsRef)
    if (has) return weakMap.get(jsRef)
    weakMap.set(jsRef, nextHashCode++)
    return nextHashCode
}