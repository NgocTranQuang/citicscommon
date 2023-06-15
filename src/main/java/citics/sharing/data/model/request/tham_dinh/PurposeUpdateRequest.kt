package citics.sharing.data.model.request.tham_dinh

import citics.sharing.data.model.response.LandPurposeResponse
import citics.sharing.data.model.response.PurposeUsingResponse

data class PurposeUpdateRequest(var record_id: String, var using_purpose: List<LandPurposeResponse>)
