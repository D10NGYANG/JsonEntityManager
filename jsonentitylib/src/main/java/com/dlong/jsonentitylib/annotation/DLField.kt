package com.dlong.jsonentitylib.annotation

/**
 * 变量注解
 *
 * @author D10NG
 * @date on 2019-10-23 15:29
 */
@kotlin.annotation.Target(AnnotationTarget.FIELD)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class DLField (
    // 在JSON中的名字
    val nameInJson: String = ""
)