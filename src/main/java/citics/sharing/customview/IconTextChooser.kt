package citics.sharing.customview

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import com.sharing.R
import com.sharing.databinding.LayoutIconTextChooserBinding

/**
 * Created by ChinhQT on 13/10/2022.
 */
class IconTextChooser @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0, defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyle, defStyleRes) {

    private var binding: LayoutIconTextChooserBinding

    var onClickListener: (() -> Unit)? = null

    init {
        binding = LayoutIconTextChooserBinding.inflate(LayoutInflater.from(context), this)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(
                it, R.styleable.IconTextChooser, 0, 0
            )

            try {
                val titleLbl = typedArray.getString(R.styleable.IconTextChooser_icon_text_title)
                val showTitleImg =
                    typedArray.getBoolean(R.styleable.IconTextChooser_icon_text_show_icon, true)
                val titleImg =
                    typedArray.getDrawable(R.styleable.IconTextChooser_icon_text_icon_img)
                val showLine =
                    typedArray.getBoolean(R.styleable.IconTextChooser_icon_text_show_line, true)
                val showEndImg =
                    typedArray.getBoolean(R.styleable.IconTextChooser_icon_text_show_end_img, true)
                val tintColor = typedArray.getColor(
                    R.styleable.IconTextChooser_icon_text_tint_img, ResourcesCompat.getColor(
                        resources, R.color.color_icon_tint, context.theme
                    )
                )
                val tintEndColor = typedArray.getBoolean(
                    R.styleable.IconTextChooser_icon_text_show_tint_end_icon, false
                )
                val titleColor = typedArray.getColor(
                    R.styleable.IconTextChooser_icon_text_title_color, ResourcesCompat.getColor(
                        resources, R.color.black, context.theme
                    )
                )
                val endImg = typedArray.getDrawable(R.styleable.IconTextChooser_icon_text_end_img)

                binding.titleLbl.text = titleLbl
                binding.titleLbl.setTextColor(titleColor)
                if (showTitleImg) {
                    binding.titleImg.visibility = View.VISIBLE
                    binding.titleImg.imageTintList = ColorStateList.valueOf(tintColor)
                } else {
                    binding.titleImg.visibility = View.GONE
                }
                binding.titleImg.setImageDrawable(titleImg)
                if (showLine) {
                    binding.lineDivider.visibility = View.VISIBLE
                } else {
                    binding.lineDivider.visibility = View.GONE
                }
                if (showEndImg) {
                    binding.endImg.visibility = View.VISIBLE
                    if (tintEndColor) {
                        binding.endImg.imageTintList = ColorStateList.valueOf(tintColor)
                    }
                } else {
                    binding.endImg.visibility = View.GONE
                }

                endImg.let {
                    binding.endImg.setImageDrawable(it)
                }

                binding.root.setOnClickListener {
                    onClickListener?.invoke()
                }
            } finally {
                typedArray.recycle()
            }
        }
    }

    fun setTitle(title: String) {
        binding.titleLbl.text = title
    }

    fun setData(titleImg: Drawable, title: String) {
        binding.titleImg.setImageDrawable(titleImg)
        binding.titleLbl.text = title
    }
}