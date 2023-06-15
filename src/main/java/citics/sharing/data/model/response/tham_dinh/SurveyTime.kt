package citics.sharing.data.model.response.tham_dinh

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

/**
 * Created by ChinhQT on 19/12/2022.
 */
@Parcelize
data class SurveyTime(
    @Json(name = "date") val date: Long? = null,
    @Json(name = "official") val official: Official? = null,
) : Parcelable {
    @Parcelize
    data class Official(
        @Json(name = "id") val id: String,
        @Json(name = "address") val address: String,
        @Json(name = "hotline") val hotline: String?,
        @Json(name = "toa_do") val toa_do: String,
        @Json(name = "lat") val lat: Double,
        @Json(name = "lng") val lng: Double,
        @Json(name = "official_name") val officialName: String,
    ) : Parcelable
}