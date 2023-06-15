package citics.sharing.data.model.response

import com.squareup.moshi.Json

/**
 * Created by ChinhQT on 26/10/2022.
 */
data class LandingPurposeResponse(val status: Boolean, val data: List<Data>) {

    data class Data(
        @Json(name = "value") val value: String,
        @Json(name = "description") val description: String?,
        @Json(name = "field_maps") val field_maps: List<Item>?
    )

    data class Item(
        @Json(name = "field") val field: String,
        @Json(name = "description") val description: String?
    )
}