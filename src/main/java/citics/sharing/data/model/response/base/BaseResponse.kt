package citics.sharing.data.model.response.base

open class BaseResponse<T, E> {
    val status: Boolean? = null
    val message: String? = null
    val title: String? = null
    var data: T? = null
    val pagination: Any? = null
    var extra: E? = null
}