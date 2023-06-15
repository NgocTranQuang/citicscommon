//package citics.sharing.data.repository.datasource.remote.base
//
//class BaseResponse<T:Any, E:Any> {
//    /*Check api thành công hay thất bại.
//    Để kiểu dữ liệu Any thì lúc thành công nó là kiểu boolean, lúc thất bại thì nó là kiểu Int*/
//    val status: Any? = null
//
//    // Scope api thành công
//    val data: T? = null
//    val pagination: Any? = null
//    val extra: E? = null
//
//    // Scope api thất bại
//    val message: String? = null // Message khi call api thất bại
//    val title: String? = null // Title khi call api thất bại
//    val detail: String? = null // Message khi call api thất bại
//    val code: Int? = null // Code khi call api thất bại
//    val name: String? = null // Code khi call api thất bại
//    val description: String? = null // description khi call api thất bại (Hầu như toàn bằng null)
//}