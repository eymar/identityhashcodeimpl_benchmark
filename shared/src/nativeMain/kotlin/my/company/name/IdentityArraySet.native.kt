package my.company.name

import kotlin.experimental.ExperimentalNativeApi
import kotlin.native.identityHashCode

@OptIn(ExperimentalNativeApi::class)
actual fun identityHashCode(instance: Any?): Int {
    return instance.identityHashCode()
}