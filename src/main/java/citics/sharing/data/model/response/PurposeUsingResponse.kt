package citics.sharing.data.model.response

import com.squareup.moshi.Json

/**
 * Created by ChinhQT on 26/10/2022.
 */
data class PurposeUsingResponse(val status: Boolean, val data: List<Data>) {

    data class Data(
        @Json(name = "name") val name: String,
        @Json(name = "code") val code: String?,
        @Json(name = "ignore_pricing") val ignorePricing: Boolean?,
        @Json(name = "is_agriculture") val isAgriculture: Boolean?,
        @Json(name = "can_use_time_limit") val canUseTimeLimit: Boolean,
        @Json(name = "usable_till") val usableTill: Long?,
        @Json(name = "is_main") val isMain: Boolean?
    )
}