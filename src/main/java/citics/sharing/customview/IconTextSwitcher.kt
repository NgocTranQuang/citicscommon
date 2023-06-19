package citics.sharing.customview

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.CompoundButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import com.sharing.R
import com.sharing.databinding.LayoutIconTextSwitcherBinding

/**
 * Created by ChinhQT on 13/10/2022.
 */
class IconTextSwitcher @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0, defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyle, defStyleRes) {

    private var binding: LayoutIconTextSwitcherBinding

    var onClickListener: (() -> Unit)? = null

    init {
        binding = LayoutIconTextSwitcherBinding.inflate(LayoutInflater.from(context), this)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(
                it, R.styleable.IconTextSwitcher, 0, 0
            )

            try {
                val titleLbl = typedArray.getString(R.styleable.IconTextSwitcher_icon_switcher_title)
                val showTitleImg =
                    typedArray.getBoolean(R.styleable.IconTextSwitcher_icon_switcher_show_icon, true)
                val titleImg =
                    typedArray.getDrawable(R.styleable.IconTextSwitcher_icon_switcher_icon_img)
                val showLine =
                    typedArray.getBoolean(R.styleable.IconTextSwitcher_icon_switcher_show_line, false)
                val tintColor = typedArray.getColor(
                    R.styleable.IconTextSwitcher_icon_switcher_tint_img, ResourcesCompat.getColor(
                        resources, R.color.color_icon_tint, context.theme
                    )
                )
                val tintEndColor = typedArray.getBoolean(
                    R.styleable.IconTextSwitcher_icon_switcher_show_tint_end_icon, false
                )

                binding.vSwitch.text = titleLbl
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

                binding.root.setOnClickListener {
                    onClickListener?.invoke()
                }
            } finally {
                typedArray.recycle()
            }
        }
    }

    fun setTitle(title: String) {
        binding.vSwitch.text = title
    }

    fun setData(titleImg: Drawable, title: String) {
        binding.titleImg.setImageDrawable(titleImg)
        binding.vSwitch.text = title
    }

    fun isChecked(): Boolean{
        return binding.vSwitch.isChecked
    }

    fun toggleSwitcher(){
        binding.vSwitch.toggle()
    }

    fun setOnSwitcherCheckedChange(listener: CompoundButton.OnCheckedChangeListener){
        binding.vSwitch.setOnCheckedChangeListener(listener)
    }

}