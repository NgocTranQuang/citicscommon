package citics.sharing.customview

import android.content.Context
import android.content.res.TypedArray
import android.text.TextUtils
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.citics.cbank.R
import citics.sharing.extension.toFormat

class TextViewUnit @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    private var mUnit: String = ""
    private var defaultValue: String = ""
    private var preventFormatNumber: Boolean = false

    init {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        attrs?.let {
            val typedArray: TypedArray =
                context.obtainStyledAttributes(attrs, R.styleable.TextViewUnit)
            try {
                mUnit = typedArray.getString(
                    R.styleable.TextViewUnit_text_view_unit
                ) ?: ""
                preventFormatNumber = typedArray.getBoolean(
                    R.styleable.TextViewUnit_text_view_prevent_format_number,
                    false
                )
            } finally {
                typedArray.recycle()
            }
        }
    }

    fun setUnit(unit: String) {
        this.mUnit = unit
    }

    fun setDefaultValue(defaultValue: String) {
        this.defaultValue = defaultValue
    }

    fun setValue(text: String?) {
        var textCompare = text
        if (textCompare == "null" || TextUtils.isEmpty(textCompare)) {
            textCompare = null
        }
        val newText = ((textCompare ?: defaultValue).toString() + " " + mUnit).trim()
        setText(newText)
    }

    fun setDataYellowColor(any: Any?) {
        if (any != null) {
            if (any is Map<*, *>) {
                any["_new"]?.let {
                    setTextChangeColor(it.toFormat())
                }
            } else {
                setTextChangeColor(any.toFormat())
            }
        }
    }

    fun setTitleColor(color: Int) {
        setTextColor(context.getColor(color))
    }

    private fun setTextChangeColor(text: String, color: Int = R.color.color_money) {
        var newText = text
        if (preventFormatNumber) {
            newText = text.replace(",", "")
        }
        setValue(newText)
        setTextColor(context.getColor(color))
    }

    fun setPreventFormatNumber(preventFormatNumber: Boolean) {
        this.preventFormatNumber = preventFormatNumber
    }

}
