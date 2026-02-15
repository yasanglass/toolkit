package glass.yasan.toolkit.core

@JsName("window")
internal external object Window {
    fun open(url: String, target: String)
}