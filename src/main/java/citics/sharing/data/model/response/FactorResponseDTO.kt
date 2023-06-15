package citics.sharing.data.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class FactorResponseDTO(
    @Json(name = "factor_id") val id: String? = null,
    @Json(name = "factor_title") val title: String? = null,
) : Parcelable
