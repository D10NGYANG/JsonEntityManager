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

    /**
     * 在JSON中的Key值
     */
    val nameInJson: String = "",

    /**
     * 数值在JSON中的进制格式
     * # 在当前变量的类型为Int型时起效
     */
    val radixInJson: Int = 10
)