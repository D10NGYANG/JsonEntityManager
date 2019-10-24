package com.dlong.jsonentitylib

import com.dlong.jsonentitylib.annotation.DLField
import com.dlong.jsonentitylib.utils.TypeMatch
import org.json.JSONArray
import org.json.JSONObject
import java.io.Serializable
import java.lang.reflect.Modifier
import java.lang.reflect.ParameterizedType

/**
 * 基础类
 *
 * @author D10NG
 * @date on 2019-10-23 15:25
 */
open class BaseJsonEntity : Serializable {

    /**
     * 从JSON中读取数据
     * @param json
     * @param excludeNames 排除的变量
     */
    open fun setFromJson(json: JSONObject, vararg excludeNames: String) {
        val nameList = excludeNames.asList()
        // 获取所有字段，不包含父类的
        val fs = this.javaClass.declaredFields
        for (f in fs) {
            // 排除不符合的变量
            val annotation = f.getAnnotation(DLField::class.java) ?: continue
            if (nameList.contains(f.name)) continue
            val modifier = Modifier.toString(f.modifiers)
            if (modifier.contains("static") || modifier.contains("final")) continue
            // 提取json中的字段名
            var name = annotation.nameInJson
            if (name.isEmpty()) {
                name = f.name
            }
            // 将private变量改成外部类可读
            f.isAccessible = true
            // 判断变量类型
            when {
                TypeMatch.isString(f.type) -> f.set(this, json.optString(name))
                TypeMatch.isInt(f.type) -> f.setInt(this, json.optInt(name))
                TypeMatch.isLong(f.type) -> f.setLong(this, json.optLong(name))
                TypeMatch.isDouble(f.type) -> f.setDouble(this, json.optDouble(name))
                TypeMatch.isBoolean(f.type) -> f.setBoolean(this, json.optBoolean(name))
                TypeMatch.isList(f.type) -> {
                    // List 类型，去获取其中的泛型
                    val paramType = f.genericType as ParameterizedType
                    // 得到泛型里的class类型对象
                    val typeClz = paramType.actualTypeArguments[0] as Class<*>
                    f.set(this, getListFromJsonArray(typeClz, json.getJSONArray(name)))
                }
                TypeMatch.isBaseJsonEntity(f.type) -> {
                    // 同样继承了当前类的变量
                    val obj = f.type.newInstance()
                    val method = f.type.getMethod("setFromJson",
                        JSONObject::class.java, Array<String>::class.java)
                    val array = emptyArray<String>()
                    method.invoke(obj, json.optJSONObject(name), array)
                    f.set(this, obj)
                }
                else -> f.set(this, json.opt(name))
            }
        }
    }

    // 获取array数据
    private fun getListFromJsonArray(clz: Class<*>, array: JSONArray) : List<Any> {
        val list: MutableList<Any> = arrayListOf()
        for (i in 0 until array.length()) {
            when {
                TypeMatch.isString(clz) -> list.add(array.optString(i))
                TypeMatch.isInt(clz) -> list.add(array.optInt(i))
                TypeMatch.isLong(clz) -> list.add(array.optLong(i))
                TypeMatch.isDouble(clz) -> list.add(array.optDouble(i))
                TypeMatch.isBoolean(clz) -> list.add(array.optBoolean(i))
                TypeMatch.isBaseJsonEntity(clz) -> {
                    // 同样继承了当前类的变量
                    val obj = clz.newInstance()
                    val method = clz.getMethod("setFromJson",
                        JSONObject::class.java, Array<String>::class.java)
                    val temp = emptyArray<String>()
                    method.invoke(obj, array.optJSONObject(i), temp)
                    list.add(obj)
                }
            }
        }
        return list
    }

    /**
     * 转换为JSON
     * @param excludeNames 排除的变量
     */
    open fun toJson(vararg excludeNames: String) : JSONObject {
        val json = JSONObject()
        val nameList = excludeNames.asList()
        // 获取所有字段，不包含父类的
        val fs = this.javaClass.declaredFields
        for (f in fs) {
            // 排除不符合的变量
            val annotation = f.getAnnotation(DLField::class.java) ?: continue
            if (nameList.contains(f.name)) continue
            val modifier = Modifier.toString(f.modifiers)
            if (modifier.contains("static") || modifier.contains("final")) continue
            // 提取json中的字段名
            var name = annotation.nameInJson
            if (name.isEmpty()) {
                name = f.name
            }
            // 将private变量改成外部类可读
            f.isAccessible = true
            // 判断变量类型
            when {
                TypeMatch.isList(f.type) -> {
                    // List 类型，去获取其中的泛型
                    val paramType = f.genericType as ParameterizedType
                    // 得到泛型里的class类型对象
                    val typeClz = paramType.actualTypeArguments[0] as Class<*>
                    var value = f.get(this) as List<Any>?
                    if (value == null) value = emptyList()
                    json.put(name, toJsonArray(typeClz, value))
                }
                TypeMatch.isBaseJsonEntity(f.type) -> {
                    // 同样继承了当前类的变量
                    val instance = f.get(this)
                    val method = f.type.getMethod("toJson", Array<String>::class.java)
                    val temp: JSONObject = method.invoke(instance, emptyArray<String>()) as JSONObject
                    json.put(name, temp)
                }
                else -> json.put(name, f.get(this))
            }
        }
        return json
    }

    // list转换为JSONArray
    private fun toJsonArray(clz: Class<*>, value: List<Any>): JSONArray {
        val array = JSONArray()
        for (i in value.indices) {
            if (TypeMatch.isBaseJsonEntity(clz)) {
                // 同样继承了当前类的变量
                val method = clz.getMethod("toJson", Array<String>::class.java)
                val temp: JSONObject = method.invoke(value[i], emptyArray<String>()) as JSONObject
                array.put(temp)
            } else {
                array.put(value[i])
            }
        }
        return array
    }
}