package citics.sharing.customview

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.citics.cbank.R
import com.citics.cbank.databinding.LayoutSearchBinding

/**
 * Created by ChinhQT on 09/10/2022.
 */
class SearchLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0, defStyleRes: Int = 0
) : CardView(context, attrs, defStyle) {

    private var binding: LayoutSearchBinding

    init {
        binding = LayoutSearchBinding.inflate(LayoutInflater.from(context), this)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(
                it, R.styleable.SearchLayout, 0, 0
            )
            radius = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                15f,
                context.resources.displayMetrics
            )
            cardElevation = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                8f,
                context.resources.displayMetrics
            )
            try {
                val inputHint = typedArray.getString(R.styleable.SearchLayout_android_hint)
                binding.textInput.hint = inputHint
            } finally {
                typedArray.recycle()
            }
        }
    }

    fun getTextInput() = binding.textInput
}