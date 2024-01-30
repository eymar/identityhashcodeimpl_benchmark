package my.company.name

private var nextHash = 1
private const val IDENTITY_HASHCODE_FIELD = "kotlinIdentityHashcodeValue$"


actual fun identityHashCode(instance: Any?): Int {
    if (instance == null) {
        return 0
    }

    val hashCode = instance.asDynamic()[IDENTITY_HASHCODE_FIELD]
    if (hashCode != null) {
        return hashCode
    }

    return when (jsTypeOf(instance)) {
        "object", "function" -> memoizeIdentityHashCode(instance)
        else -> throw IllegalArgumentException(
            "identity hash code for ${jsTypeOf(instance)} is not supported"
        )
    }
}

private fun memoizeIdentityHashCode(instance: Any?): Int {
    val value = nextHash++

    val descriptor = js("new Object()")
    descriptor.value = value
    descriptor.writable = false
    descriptor.configurable = false
    descriptor.enumerable = false

    js("Object").defineProperty(instance, IDENTITY_HASHCODE_FIELD, descriptor)

    return value
}