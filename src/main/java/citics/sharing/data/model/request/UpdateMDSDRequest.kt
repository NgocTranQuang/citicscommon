package citics.sharing.data.model.request

import citics.sharing.data.model.response.DetailUsingPurpose

/**
 * Created by ChinhQT on 28/12/2022.
 */
data class UpdateMDSDRequest(val using_purpose: List<DetailUsingPurpose>, val record_id: String)