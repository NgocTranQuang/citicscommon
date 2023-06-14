package citics.sharing.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.citics.cbank.databinding.LayoutAddButtonBinding
import com.sharing.R


/**
 * Created by ChinhQT on 25/10/2022.
 */

class ButtonAddNewLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private var binding: LayoutAddButtonBinding

    init {
        binding = LayoutAddButtonBinding.inflate(LayoutInflater.from(context), this)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(
                it, R.styleable.ButtonAddNewLayout, 0, 0
            )

            try {
                val title =
                    typedArray.getString(R.styleable.ButtonAddNewLayout_button_text)
                        ?: context.getString(R.string.xac_nhan_them_moi)
                binding.tvTitle.text = title

            } finally {
                typedArray.recycle()
            }
        }
    }
}