package citics.sharing.data.model.request.tham_dinh

import citics.sharing.data.model.response.tham_dinh.Properties

/**
 * Created by ChinhQT on 10/01/2023.
 */
data class UpdateLocationComparedCanHoRequest(
    val properties: Properties? = null, val record_id: String? = null, val can_ho: CanHo? = null
)

data class UpdateLocationComparedCHRequest(
    val properties: Properties? = null,
    val record_id: String? = null,
    val can_ho: CanHo? = null,
    val id: String? = null
)