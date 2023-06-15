package com.citics.valuation.data.model.response

/**
 * Created by ChinhQT on 09/10/2022.
 */
data class ErrorResponse(
    val type: String = "",
    var title: String = "",
    val status: Int = 0,
    var code: Int? = null,
    val detail: String = "",
    var message: String = "",
    var required_fields: RequiredFields? = null,
    // Phần dành riêng cho update location thẩm định tài sản chính
    val ranh_gioi_wgs84: List<List<Double>>? = null,
    val ranh_gioi_vn2000: List<List<Double>>? = null,
)