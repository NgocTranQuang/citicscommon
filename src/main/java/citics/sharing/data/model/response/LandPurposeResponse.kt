package citics.sharing.data.model.response

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
class LandPurposeResponse(
    @Json(name = "name") val name: String,
    @Json(name = "code") val code: String?,
    @Json(name = "ignore_pricing") val ignorePricing: Boolean?,
    @Json(name = "is_agriculture") val isAgriculture: Boolean?,
    @Json(name = "can_use_time_limit") val canUseTimeLimit: Boolean?,
    @Json(name = "usable_till") val usableTill: Long?,
    @Json(name = "is_main") val isMain: Boolean?,
) : Parcelable