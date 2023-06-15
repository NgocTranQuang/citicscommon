package citics.sharing.data.model.response

import citics.sharing.data.model.response.base.BaseLoadMoreResponse

data class NotificationResponseDTO(
    val notice: BaseLoadMoreResponse<ContentNotificationDTO>? = null,
    val nBadge: Int? = null
)

data class ContentNotificationDTO(
    val id: String? = null,
    val title: String? = null,
    val body: String? = null,
    val contentId: String? = null,
    val contentType: String? = null,
    val action: String? = null,
    var read: Boolean? = null,
    val timestamp: Long? = null,
    val owner: String? = null,
    val image: String? = null,
    val messageContentType: String? = null,
    val created_date: String? = null,
    val cpoint: Int? = null,
    val cpoint_str: String? = null
)
