package citics.sharing.data.model.response.cpoint

import com.squareup.moshi.Json

data class CPointListResponse(
    val text: String? = null,
    val created_date: Long? = null,
    val sub_text: String? = null,
    val action_type: String? = null,
    @Json(name = "c-point")
    val cPoint: Int? = null
)
