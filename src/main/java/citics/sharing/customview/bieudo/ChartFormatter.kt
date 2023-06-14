package citics.sharing.customview.bieudo

import com.github.mikephil.charting.formatter.ValueFormatter

class ChartFormatter : ValueFormatter() {
    override fun getFormattedValue(value: Float): String {
        return value.toInt().toString()
    }

}