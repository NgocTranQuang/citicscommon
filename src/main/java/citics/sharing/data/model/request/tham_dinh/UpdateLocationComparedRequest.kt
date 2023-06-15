package citics.sharing.data.model.request.tham_dinh

import citics.sharing.data.model.request.VN2000Request
import citics.sharing.data.model.response.tham_dinh.Properties

/**
 * Created by ChinhQT on 10/01/2023.
 */
data class UpdateLocationComparedRequest(
    val properties: Properties? = null,
    val record_id: String? = null,
    val nha_pho: NhaPho? = null,
    val can_ho: CanHo? = null
)

data class UpdateLocationComparedRequestForTSSS(
    val properties: Properties? = null,
    val record_id: String? = null,
    val id: String? = null,
    val nha_pho: NhaPho? = null,
    val can_ho: CanHo? = null
)

data class NhaPho(val status: String? = null, val coordinates: List<VN2000Request.XY>? = null)