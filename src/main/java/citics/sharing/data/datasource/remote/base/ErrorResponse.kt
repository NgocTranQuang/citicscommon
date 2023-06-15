//package citics.sharing.data.repository.datasource.remote.base
//
//import citics.sharing.data.repository.datasource.remote.extra.RequiredFields
//
///**
// * Created by ChinhQT on 09/10/2022.
// */
//data class ErrorResponse(
//    val type: String = "",
//    var title: String = "",
//    val status: Int = 0,
//    var code: Int? = null,
//    val detail: String = "",
//    var message: String = "",
//
//    var required_fields: RequiredFields? = null,
//    // Phần dành riêng cho change location tài sản chính tài sản thẩm định
//    val ranh_gioi_wgs84: List<List<Double>>? = null,
//    val ranh_gioi_vn2000: List<List<Double>>? = null,
//)