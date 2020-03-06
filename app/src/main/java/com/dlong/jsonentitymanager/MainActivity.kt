package com.dlong.jsonentitymanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // {"int1":81,"listT":["1a","2b","3c"],"stringT":"abcd","test2":{"para1":"efgh","para2":1234,"para3":true,"para4":["1a","2b","3c"]},"testList":[{"para1":"L0","para2":0,"para3":false,"para4":["2a","3b","4c"]},{"para1":"L1","para2":1,"para3":true,"para4":["3a","4b","5c"]}]}
        val str = "{\"int1\":81,\"listT\":[\"1a\",\"2b\",\"3c\"],\"stringT\":\"abcd\",\"test2\":{\"para1\":\"efgh\",\"para2\":1234,\"para3\":true,\"para4\":[\"1a\",\"2b\",\"3c\"]},\"testList\":[{\"para1\":\"L0\",\"para2\":0,\"para3\":false,\"para4\":[\"2a\",\"3b\",\"4c\"]},{\"para1\":\"L1\",\"para2\":1,\"para3\":true,\"para4\":[\"3a\",\"4b\",\"5c\"]}]}"
        val json = JSONObject(str)

        val test = TestInfo()
        test.setFromJson(json)
        Log.e("测试", "data=$test")
        Log.e("测试", "json=${test.toJson()}")
    }
}
