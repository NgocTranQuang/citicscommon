package citics.sharing.data.model.request

/**
 * Created by ChinhQT on 03/01/2023.
 */
data class CustomerRequest(
    val customer: Customer? = null, val id: String? = null
) {
    data class Customer(
        val appliedDate: String? = null,
        val appliedPlace: String? = null,
        val avatar: String? = null,
        val business: BusinessRequest? = null,
        val diaChi: String? = null,
        val email: String? = null,
        val fullName: String? = null,
        val idPersonal: String? = null,
        val identityCardIds: List<String>? = null,
        val ma_phuong: String? = null,
        val ma_quan: String? = null,
        val ma_thanh_pho: String? = null,
        val note: String? = null,
        val phoneNo: String? = null,
        val phuong: String? = null,
        val quan: String? = null,
        val so_nha: String? = null,
        val thanh_pho: String? = null,
        val type: String? = null,
        val vi_tri_duong: String? = null
    )
}