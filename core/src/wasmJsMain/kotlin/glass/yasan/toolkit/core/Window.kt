package glass.yasan.toolkit.core

@JsName("window")
internal external object Window {
    @OptIn(ExperimentalWasmJsInterop::class)
    fun open(url: String, target: String): JsAny?
}