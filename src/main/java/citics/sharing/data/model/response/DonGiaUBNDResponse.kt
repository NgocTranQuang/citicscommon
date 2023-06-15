package citics.sharing.data.model.response

import com.squareup.moshi.Json

/**
 * Created by ChinhQT on 08/11/2022.
 */
data class DonGiaUBNDResponse(
    @Json(name = "ma_quan") var ma_quan: String? = null,
    @Json(name = "vt_list") var vt_list: List<Data>? = null
) {
    data class Data(
        @Json(name = "vi_tri_theo_ubnd") var vi_tri_theo_ubnd: String? = null,
        @Json(name = "item_list") var item_list: List<Item>? = null
    )

    data class Item(
        @Json(name = "using_purpose") var using_purpose: String? = null,
        @Json(name = "don_gia_ubnd") var don_gia_ubnd: Long? = null
    )
}