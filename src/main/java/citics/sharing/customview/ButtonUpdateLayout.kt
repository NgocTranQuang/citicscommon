package citics.sharing.customview

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.citics.cbank.R
import com.citics.cbank.databinding.LayoutButtonUpdateBinding
import com.google.android.material.card.MaterialCardView
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.CornerSize
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel


/**
 * Created by ChinhQT on 25/10/2022.
 */

class ButtonUpdateLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : MaterialCardView(context, attrs) {

    private var binding: LayoutButtonUpdateBinding

    init {
        binding = LayoutButtonUpdateBinding.inflate(LayoutInflater.from(context), this)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(
                it, R.styleable.ButtonUpdateLayout, 0, 0
            )

            try {
                val title =
                    typedArray.getString(R.styleable.ButtonUpdateLayout_button_update_layout_text)
                        ?: context.getString(R.string.update)
                binding.btnUpdate.text = title

                val leftShapePathModel = ShapeAppearanceModel().toBuilder()
                // You can change style and size
                leftShapePathModel.setTopLeftCorner(
                    CornerFamily.ROUNDED,
                    CornerSize { return@CornerSize 30F })

                leftShapePathModel.setTopRightCorner(
                    CornerFamily.ROUNDED,
                    CornerSize { return@CornerSize 30F })

                val bg = MaterialShapeDrawable(leftShapePathModel.build())
                // In my screen without applying color it shows black background
                bg.fillColor = ColorStateList.valueOf(
                    ContextCompat.getColor(context, R.color.white)
                )
                bg.elevation = 100f
                cardElevation = 100f
                background = bg
                invalidate()
            } finally {
                typedArray.recycle()
            }
        }
    }


    fun onClickListener(click: OnClickListener) {
        binding.btnUpdate.setOnClickListener(click)
    }

    override fun setOnClickListener(l: OnClickListener?) {
        binding.btnUpdate.setOnClickListener(l)
    }

    fun disableButton() {
        binding.btnUpdate.isEnabled = false
        binding.btnUpdate.isClickable = false
    }

    fun enableButton() {
        binding.btnUpdate.isEnabled = true
        binding.btnUpdate.isClickable = true
    }

    fun setTitleButton(text: String) {
        binding.btnUpdate.text = text
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        cardElevation = 30f

    }
}