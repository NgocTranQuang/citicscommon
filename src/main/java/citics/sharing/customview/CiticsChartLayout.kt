package citics.sharing.customview

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import citics.sharing.customview.bieudo.ChartFormatter
import com.citics.cbank.databinding.LayoutCiticsChartBinding
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.components.YAxis.AxisDependency
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.utils.ColorTemplate

/**
 * Created by ChinhQT on 25/10/2022.
 */
class CiticsChartLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0, defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    private var binding: LayoutCiticsChartBinding

    init {
        binding = LayoutCiticsChartBinding.inflate(LayoutInflater.from(context), this)
        setDataForChart()
    }

    private fun setDataForChart() {
        val chart = binding.chart

        chart.description.isEnabled = false

        chart.setTouchEnabled(true)

        chart.dragDecelerationFrictionCoef = 0.9f

        chart.isDragEnabled = true
        chart.setScaleEnabled(true)
        chart.isHighlightPerDragEnabled = true

        chart.setBackgroundColor(Color.WHITE)
        chart.setDrawGridBackground(false)

        val l = chart.legend
        l.isEnabled = false

        val xAxis = chart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM_INSIDE
        xAxis.textSize = 10f
        xAxis.textColor = Color.BLACK
        xAxis.setDrawAxisLine(false)
        xAxis.setDrawGridLines(false)
        xAxis.setCenterAxisLabels(true)
        xAxis.granularity = 1f // one hour
        xAxis.valueFormatter = ChartFormatter()
        val leftAxis = chart.axisRight
        leftAxis.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART)
        leftAxis.setDrawGridLines(false)
        leftAxis.isGranularityEnabled = true
        leftAxis.axisMinimum = 0f
        leftAxis.axisMaximum = 20f
        leftAxis.yOffset = 19f
        leftAxis.textColor = Color.BLACK
        leftAxis.setDrawAxisLine(false)

        val rightAxis = chart.axisLeft
        rightAxis.setDrawZeroLine(false)
        rightAxis.isEnabled = false
        rightAxis.setDrawGridLines(false)
        setData(mutableListOf())
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
    }


    fun setData(data: MutableList<Entry>?) {
        val chart = binding.chart
        val chartData = getListChartData()
        chartData.setValueTextSize(9f)
        chart.data = chartData
    }

    private fun getListChartData(): LineData {
        val values = ArrayList<Entry>()
        values.add(Entry(2012f, 0f))
        values.add(Entry(2014f, 9f))
        values.add(Entry(2016f, 13f))
        values.add(Entry(2018f, 7f))
        values.add(Entry(2020f, 19f))

        // create a dataset and give it a type

        // create a dataset and give it a type
        val set1 = LineDataSet(values, "DataSet 1")

        set1.axisDependency = AxisDependency.LEFT
        set1.color = ColorTemplate.getHoloBlue()
        set1.valueTextColor = ColorTemplate.getHoloBlue()
        set1.lineWidth = 1.5f
        set1.setDrawCircles(true)
        set1.setDrawValues(true)
        set1.fillAlpha = 65
        set1.fillColor = ColorTemplate.getHoloBlue()
        set1.isHighlightEnabled = false
        set1.setDrawCircleHole(true)
        val data = LineData(set1)
        return data

    }
}

