package com.dlong.jsonentitymanager

import com.dlong.jsonentitylib.BaseJsonEntity
import com.dlong.jsonentitylib.annotation.DLField

/**
 * @author D10NG
 * @date on 2019-10-23 16:53
 */
class TestInfo : BaseJsonEntity() {
    @DLField(nameInJson = "int1")
    var intT: Int = 0
    @DLField
    var stringT: String = ""
    @DLField
    var listT: List<String> = emptyList()
    @DLField
    var test2: Test2Info = Test2Info()
    @DLField
    var testList: List<Test2Info> = emptyList()
}