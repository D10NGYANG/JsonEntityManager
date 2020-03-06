package com.dlong.jsonentitymanager

import com.dlong.jsonentitylib.BaseJsonEntity
import com.dlong.jsonentitylib.annotation.DLField

/**
 * @author D10NG
 * @date on 2019-10-23 16:53
 */
data class TestInfo (

    // 使用nameInJson标注在JSON中对应的字段
    @DLField(nameInJson = "int1", radixInJson = 16)
    var intT: Int = 0,

    @DLField
    var stringT: String = "",

    // 这是一个普通Array，用于读取JSON中的JSONArray
    @DLField
    var listT: List<String> = emptyList(),

    // 这是另一个继承了BaseJsonEntity()的变量
    @DLField
    var test2: Test2Info = Test2Info(),

    // 这是继承了BaseJsonEntity()的变量列表
    @DLField
    var testList: List<Test2Info> = emptyList()
) : BaseJsonEntity()