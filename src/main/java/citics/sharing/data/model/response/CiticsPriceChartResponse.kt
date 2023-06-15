package citics.sharing.data.model.response

/**
 * Created by ChinhQT on 11/11/2022.
 */
data class CiticsPriceChartResponse(val status: Boolean, val data: Data? = null) {

    data class Data(val id: String? = null, val type: String? = null, val lines: List<Item>? = null)

    data class Item(val name: String? = null, val data: List<SubItem>? = null)

    data class SubItem(val date: String? = null, val value: Float? = null)
}