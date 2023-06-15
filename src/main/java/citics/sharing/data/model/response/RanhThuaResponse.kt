package citics.sharing.data.model.response

import com.squareup.moshi.Json

/**
 * Created by ChinhQT on 24/10/2022.
 */
data class RanhThuaResponse(
    val status: Boolean, val data: List<RanhThuaItem>
) {
    data class RanhThuaItem(
        @Json(name = "ogc_fid") val ogc_fid: String,
        @Json(name = "properties") val properties: Properties
    )

    data class Properties(
        @Json(name = "boundary") val boundary: List<CoordinateItem>,
        @Json(name = "kinh_do") val kinh_do: Double,
        @Json(name = "vi_do") val vi_do: Double,
        @Json(name = "estimate_price") var estimate_price: Long,
        @Json(name = "estimate_unit_price") val estimate_unit_price: Long,
    )
}
