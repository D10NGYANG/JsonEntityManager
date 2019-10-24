package com.dlong.jsonentitymanager

import com.dlong.jsonentitylib.BaseJsonEntity
import com.dlong.jsonentitylib.annotation.DLField

/**
 * @author D10NG
 * @date on 2019-10-24 08:58
 */
class Test2Info : BaseJsonEntity() {

    @DLField
    var para1 = "0"
    @DLField
    var para2 = 2
    @DLField
    var para3 = false
}