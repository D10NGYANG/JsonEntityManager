# JsonEntityManager
[![](https://jitpack.io/v/D10NGYANG/JsonEntityManager.svg)](https://jitpack.io/#D10NGYANG/JsonEntityManager)
 用于解决JSON数据转换实体类的库文件
# 使用方法
你可以直接复制上面的代码进你的工程使用，也可以添加依赖库的形式
## 添加依赖
1、在根目录的`build.gradle`里插入

```kotlin
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
2、在app的`build.gradle`里插入

```kotlin
	dependencies {
	        implementation 'com.github.D10NGYANG:JsonEntityManager:1.4'
	}
```
## 定义实体类继承BaseJsonEntity
测试实体类
```kotlin
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
```
另一个
```kotlin
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
```
## demo

```kotlin
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
```
## 混淆规则

``` 
-keep class com.dlong.jsonentitylib.** {*;}
-dontwarn com.dlong.jsonentitylib.**
-keep class * extends com.dlong.jsonentitylib.BaseJsonEntity {*;}
-keepclassmembers class * {
    @com.dlong.jsonentitylib.annotation.DLField <fields>;
}
```
