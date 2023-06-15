package citics.sharing.data.model.response

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

/**
 * Created by ChinhQT on 18/11/2022.
 */
@Parcelize
data class LandSuggestionItem(
    @Json(name = "id") val id: String = "",
    @Json(name = "source") val source: String = "",
    @Json(name = "text") val term: String = "",
    @Json(name = "extra") val latLng: LandSuggestionResponse.LatLng = LandSuggestionResponse.LatLng(),
    @Json(name = "sub_text") val address: String = ""
) : Parcelable