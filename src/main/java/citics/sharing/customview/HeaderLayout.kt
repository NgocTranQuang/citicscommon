package citics.sharing.customview

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import citics.sharing.extension.loadByName
import citics.sharing.extension.setHtml
import timber.log.Timber
import com.citics.cbank.R
import com.citics.cbank.databinding.LayoutHeaderBinding

/**
 * Created by ChinhQT on 09/10/2022.
 */
class HeaderLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0, defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyle, defStyleRes) {

    private var binding: LayoutHeaderBinding

    var onBackClickListener: (() -> Unit)? = null

    var onRightClickListener: (() -> Unit)? = null

    init {
        binding = LayoutHeaderBinding.inflate(LayoutInflater.from(context), this)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(
                it, R.styleable.HeaderLayout, 0, 0
            )

            try {
                val headerTitle = typedArray.getString(R.styleable.HeaderLayout_header_title)
                val headerRightButtonShow =
                    typedArray.getBoolean(R.styleable.HeaderLayout_header_right_button_show, false)
                val headerSubTextShow =
                    typedArray.getBoolean(R.styleable.HeaderLayout_header_sub_text_show, false)
                val rightIcon = typedArray.getResourceId(R.styleable.HeaderLayout_right_icon_img, 0)
                val leftIcon = typedArray.getResourceId(R.styleable.HeaderLayout_left_icon_img, 0)

                binding.titleTxt.text = headerTitle
                binding.rightBtn.visibility = if (headerRightButtonShow) View.VISIBLE else View.GONE
                binding.subtitleTxt.visibility = if (headerSubTextShow) View.VISIBLE else View.GONE
                binding.rightBtn.setImageResource(rightIcon)
                if (leftIcon != 0) {
                    binding.backBtn.setImageResource(leftIcon)
                }
            } finally {
                typedArray.recycle()
            }
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        Timber.d("onFinishInflate")

        binding.backBtn.setOnClickListener {
            onBackClickListener?.invoke()
        }

        binding.rightBtn.setOnClickListener {
            onRightClickListener?.invoke()
        }
    }

    fun setTitle(title: String) {
        binding.titleTxt.text = title
    }

    fun getTitle(): String {
        return binding.titleTxt.text.toString()
    }

    fun setRightImage(level: String?, isShadow: Boolean = true) {
        level?.let {
            binding.rightBtn.visibility = View.VISIBLE
            binding.rightBtn.loadByName("ic_lv_${level}")

            if (!isShadow) {
                binding.rightBtn.setBackgroundResource(R.drawable.background_corner_gray_header)
            }
        } ?: kotlin.run {
            binding.rightBtn.visibility = View.GONE
        }
    }

    fun setRightImage(iconId: Int, onClick: OnClickListener) {
        binding.rightBtn.visibility = View.VISIBLE
        binding.rightBtn.setImageResource(iconId)
        binding.rightBtn.setOnClickListener(onClick)
    }

    fun setTrashIcon() {
        binding.rightBtn.visibility = View.VISIBLE
        binding.rightBtn.setImageResource(R.drawable.ic_trash_delete)
        binding.rightBtn.imageTintList = ColorStateList.valueOf(Color.RED)
        binding.rightBtn.setBackgroundColor(Color.TRANSPARENT)
    }

    fun setBlueBack() {
        binding.backBtn.setImageResource(R.drawable.ic_trash_delete)
    }

    fun setSubtext(subText: String, isHTML: Boolean = false) {
        binding.subtitleTxt.visibility = View.VISIBLE
        if (isHTML) {
            binding.subtitleTxt.setHtml(subText)
        } else {
            binding.subtitleTxt.text = subText
        }
    }

    fun removeBackgroundRightIcon() {
        binding.rightBtn.background = null
    }
}