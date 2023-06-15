package citics.sharing.data.model.response

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

/**
 * Created by ChinhQT on 26/10/2022.
 */
data class DataAvailableResponse(val status: Boolean, val data: Data) {
    data class Data(
        @Json(name = "citics") val citics: Map<String, Citics>,
        @Json(name = "colors") val colors: Map<String, MutableList<Color>>
    )

    @Parcelize
    data class Citics(
        @Json(name = "name") val name: String,
        @Json(name = "code") val code: String,
        @Json(name = "districts") val districts: List<Info>,
    ) : Parcelable

    @Parcelize
    data class Info(
        @Json(name = "name") val name: String,
        @Json(name = "code") val code: String,
        @Json(name = "cadastre") val cadastre: Boolean,
        @Json(name = "estate_count") val estateCount: Int,
        @Json(name = "priced_estate_count") val pricedEstateCount: Int,
        @Json(name = "planning") val planning: Boolean,
        @Json(name = "land_planning") val landPlanning: Boolean,
        @Json(name = "apartment") val apartment: Boolean,
        @Json(name = "has_price") val hasPrice: Boolean,
    ) : Parcelable

    @Parcelize
    data class Color(
        @Json(name = "key") val key: String,
        @Json(name = "name") val name: String,
        @Json(name = "color") val color: String,
        @Json(name = "category") val category: String,
    ) : Parcelable
}