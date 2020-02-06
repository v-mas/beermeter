package com.github.vmas.beermeter.arch.util

import android.os.Bundle
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/*
 * Created by Sławomir Golonka @ ConciseSoftware on 06-02-2020.
 */
private inline fun <T : Any> Bundle.property(
    default: T,
    crossinline read: Bundle.(String, T) -> T,
    crossinline write: Bundle.(String, T) -> Unit
) = object : ReadWriteProperty<Any, T> {
    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return if (containsKey(property.name)) read(property.name, default) else default
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        this@property.write(property.name, value)
    }
}

private inline fun <T : Any> Bundle.property(
    key: String,
    default: T,
    crossinline read: Bundle.(String, T) -> T,
    crossinline write: Bundle.(String, T) -> Unit
) = object : ReadWriteProperty<Any, T> {
    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return if (containsKey(key)) read(key, default) else default
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T) {
        this@property.write(key, value)
    }
}

private inline fun <T : Any> Bundle.propertyNillable(
    default: T? = null,
    crossinline read: Bundle.(String, T?) -> T?,
    crossinline write: Bundle.(String, T?) -> Unit
) = object : ReadWriteProperty<Any, T?> {
    override fun getValue(thisRef: Any, property: KProperty<*>): T? {
        return if (containsKey(property.name)) read(property.name, default) else default
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T?) {
        this@propertyNillable.write(property.name, value)
    }
}

private inline fun <T : Any> Bundle.propertyNillable(
    key: String,
    default: T? = null,
    crossinline read: Bundle.(String, T?) -> T?,
    crossinline write: Bundle.(String, T?) -> Unit
) = object : ReadWriteProperty<Any, T?> {
    override fun getValue(thisRef: Any, property: KProperty<*>): T? {
        return if (containsKey(key)) read(key, default) else default
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T?) {
        this@propertyNillable.write(key, value)
    }
}

fun Bundle.int(default: Int = 0) = property(default, Bundle::getInt, Bundle::putInt)
fun Bundle.int(key: String, default: Int = 0) = property(key, default, Bundle::getInt, Bundle::putInt)

fun Bundle.stringN(default: String? = null) =
    propertyNillable(default, Bundle::getString, Bundle::putString)

fun Bundle.stringN(key: String, default: String? = null) =
    propertyNillable(key, default, Bundle::getString, Bundle::putString)

