package com.citics.valuation.data.model.response

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

data class ExtraData(
    // Phần này dành cho thay đổi giá trị của tài sản để change màu sang màu vàng
    @Json(name = "gia_tri_thay_doi") val gia_tri_thay_doi: Map<String, Any>? = null,

    @Json(name = "steps_validation_result") var steps_validation_result: StepsValidationResult? = null,

    @Json(name = "validation_result") val validation_result: ValidationResult? = null
)

data class ValidationResult(
    var asset: StepsValidationResult? = null,
    var compared_assets: MutableList<StepsValidationResult>? = null
)

@Parcelize
data class StepsValidationResult(
    val level: String? = null,
    val group: String? = null,
    val valid: Boolean? = null,
    val wh_asset_id: String? = null,
    val id: String? = null,
    val required_step: List<Step>? = null,
    val extra: ExtraAsset? = null

) : Parcelable

@Parcelize
data class Step(
    // Phần này dành cho all tabs
    val step: String? = null,
    var fields: List<String>? = null,
    // Phần này dành cho danh sách lands (tab chi tiết thửa đất)
    val required_lands: List<RequiredLand>? = null,
    // Phần này dành cho danh sách photo (tab hình ảnh)
    val required_group_photos: RequiredGroupPhoto? = null,
    // Phần này dành cho danh sách công trình( tab công trình xây dựng)
    val required_constructors: List<RequiredConstructors>? = null
) : Parcelable

data class RequiredFields(val fields: List<String>? = null)

@Parcelize
data class RequiredLand(val kind: String, val name: String, var fields: List<String>) : Parcelable

@Parcelize
data class RequiredGroupPhoto(val total_minimum: Int?, var titles: List<String>?) : Parcelable

@Parcelize
data class RequiredConstructors(
    val id: String,
    val loai_cong_trinh: String?,
    var fields: List<String>?
) : Parcelable


@Parcelize
data class ExtraAsset(
    val spreadsheet: Spreadsheet?
) : Parcelable


@Parcelize
data class Spreadsheet(
    val valid: Boolean? = null,
    val reason: String? = null,
    val violation_compared_asset_ids: List<String>? = null
) : Parcelable



