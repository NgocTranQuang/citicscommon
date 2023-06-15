package citics.sharing.data.model.response

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

/**
 * Created by ChinhQT on 09/11/2022.
 */
@Parcelize
data class CoordinateItem(
    @Json(name = "longitude") val longitude: Double? = null,
    @Json(name = "latitude") val latitude: Double? = null
) : Parcelable