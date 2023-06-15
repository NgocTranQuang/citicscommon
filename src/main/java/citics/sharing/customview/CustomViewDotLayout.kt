package citics.sharing.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.sharing.R
import com.sharing.databinding.LayoutCustomDotBinding

/**
 * Created by ChinhQT on 25/10/2022.
 */
class CustomViewDotLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0, defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyle, defStyleRes) {

    private var binding: LayoutCustomDotBinding

    init {
        binding = LayoutCustomDotBinding.inflate(LayoutInflater.from(context), this)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(
                it, R.styleable.CustomViewDotLayout, 0, 0
            )

            try {
                val hideBottomLine = typedArray.getBoolean(
                    R.styleable.CustomViewDotLayout_hide_bottom_line, false
                )
                if (hideBottomLine) {
                    hideBottomLine()
                } else {
                    showBottomLine()
                }
            } finally {
                typedArray.recycle()
            }
        }
    }

    fun hideBottomLine() {
        binding.lineBottom.visibility = View.GONE
    }

    fun showBottomLine() {
        binding.lineBottom.visibility = View.VISIBLE

    }

    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        try {
            binding.lnContentCustom.addView(child, index, params)
        } catch (e: Exception) {
            super.addView(child, index, params)
        }

    }
}