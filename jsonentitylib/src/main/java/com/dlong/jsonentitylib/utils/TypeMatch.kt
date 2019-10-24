package com.dlong.jsonentitylib.utils

/**
 * 类型识别工具
 *
 * @author D10NG
 * @date on 2019-10-23 16:33
 */
object TypeMatch {

    fun isString(clz: Class<*>) : Boolean {
        return clz == String::class.java
    }

    fun isBoolean(clz: Class<*>) : Boolean {
        return clz == Boolean::class.java
    }

    fun isInt(clz: Class<*>) : Boolean {
        return clz == Int::class.java ||
                clz == Integer::class.java ||
                clz == Byte::class.java ||
                clz == Short::class.java
    }

    fun isLong(clz: Class<*>) : Boolean {
        return clz == Long::class.java
    }

    fun isDouble(clz: Class<*>) : Boolean {
        return clz == Double::class.java ||
                clz == Float::class.java
    }

    fun isList(clz: Class<*>) : Boolean {
        return clz == List::class.java
    }
}