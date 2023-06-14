package citics.sharing.customview.bieudo

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import com.github.mikephil.charting.components.MarkerView
import android.widget.TextView
import com.github.mikephil.charting.utils.MPPointF
import citics.sharing.extension.toShow
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.sharing.R

class TooltipsMarkerView(
    context: Context?,
    layoutResource: Int,
    val barChart: BarChart?,
    val data: List<DashGrowthResponse.Data>?
) :
    MarkerView(context, layoutResource) {
    private val tvContent: TextView

    // callbacks everytime the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    @SuppressLint("SetTextI18n")
    override fun refreshContent(e: Entry, highlight: Highlight) {

        val positionGroup =
            barChart?.barData?.getDataSetForEntry(e)?.getEntryIndex(e as BarEntry) ?: 0
        val positionInGroup = highlight.dataSetIndex
        val group = data?.getOrNull(positionGroup)
        val clicked = group?.values?.getOrNull(positionInGroup)

        tvContent.text =
            "${clicked?.value.toShow()}/${clicked?.total?.toShow()} hồ sơ\nChiếm ${clicked?.tile.toShow()}%"

        super.refreshContent(e, highlight)
    }

    private var mOffset: MPPointF? = null

    init {

        // find your layout components
        tvContent = findViewById<View>(R.id.tvContent) as TextView
    }

    override fun getOffset(): MPPointF {
        if (mOffset == null) {
            // center the marker horizontally and vertically
            mOffset = MPPointF((-(width / 2)).toFloat(), (-height).toFloat())
        }
        return mOffset!!
    }
}