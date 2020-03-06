package com.dlong.jsonentitymanager

import com.dlong.jsonentitylib.BaseJsonEntity
import com.dlong.jsonentitylib.annotation.DLField

/**
 * @author D10NG
 * @date on 2019-10-24 08:58
 */
data class Test2Info (
    @DLField
    var para1: String = "0",
    @DLField
    var para2: Int = 2,
    @DLField
    var para3: Boolean = false,
    @DLField(radixInJson = 16)
    var para4: List<Int> = listOf()
) : BaseJsonEntity()