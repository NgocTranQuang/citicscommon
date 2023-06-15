package citics.sharing.data.model.response

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

/**
 * Created by ChinhQT on 08/11/2022.
 */
@Parcelize
data class DetailUsingPurpose(
    @Json(name = "agriculture") var agriculture: Boolean? = null,
    @Json(name = "kind") var kind: String? = null,
    @Json(name = "code") var code: String? = null,
    @Json(name = "title") var title: String? = null,
    @Json(name = "name") var name: String? = null,
    @Json(name = "using_time_type") var using_time_type: String? = null,
    @Json(name = "using_time") var using_time: String? = null,
    @Json(name = "day_month") var day_month: String? = null,
    @Json(name = "year") var year: String? = null,
    @Json(name = "is_main") var isMain: Boolean? = null,
    @Json(name = "ignore_pricing") var ignore_pricing: Boolean? = null,
    @Json(name = "is_agriculture") var is_agriculture: Boolean? = null

) : Parcelable