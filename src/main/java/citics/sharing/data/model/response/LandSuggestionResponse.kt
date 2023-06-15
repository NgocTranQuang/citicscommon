package citics.sharing.data.model.response

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

/**
 * Created by ChinhQT on 26/10/2022.
 */
@Parcelize
data class LandSuggestionResponse(
    val status: Boolean, val data: List<Data>
) : Parcelable {
    @Parcelize
    data class Data(val items: List<LandSuggestionItem>) : Parcelable

    @Parcelize
    data class LatLng(
        @Json(name = "latitude") val lat: Double = 0.0,
        @Json(name = "longitude") val lng: Double = 0.0
    ) : Parcelable
}