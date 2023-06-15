package citics.sharing.data.model.response

import android.os.Parcelable
import citics.sharing.data.model.response.tham_dinh.Properties
import kotlinx.parcelize.Parcelize

/**
 * Created by ChinhQT on 09/11/2022.
 */
@Parcelize
data class CanHoAdvancedResponse(val status: Boolean, val data: Data? = null) : Parcelable {

    @Parcelize
    data class Data(val projects: Projects? = null, val boundaries: List<CoordinateItem>? = null) :
        Parcelable

    @Parcelize
    data class Projects(
        val content: List<Content>? = null,
        val pageable: Pageable? = null,
        val totalElements: Int,
        val totalPages: Int,
        val last: Boolean,
        val first: Boolean
    ) : Parcelable

    @Parcelize
    data class Content(
        val id: String? = null,
        val properties: Properties? = null,
        val like: Boolean? = null,
        val tinh_trang: String? = null
    ) : Parcelable

    @Parcelize
    data class Pageable(val pageSize: Int, val pageNumber: Int, val offset: Int) : Parcelable
}