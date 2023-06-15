package citics.sharing.data.model.response.base

data class BaseLoadMoreResponse<T>(val content: List<T>? = null, val totalPages: Int? = null)
