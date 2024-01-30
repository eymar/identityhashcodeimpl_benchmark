package my.company.name

actual fun identityHashCode(instance: Any?): Int {
    return System.identityHashCode(instance)
}