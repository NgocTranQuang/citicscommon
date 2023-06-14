package citics.sharing.customview

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.ResourcesCompat
import timber.log.Timber
import com.citics.cbank.R
import com.citics.cbank.databinding.LayoutBaseDialogBinding
import com.citics.valuation.utils.DialogType

/**
 * Created by ChinhQT on 09/10/2022.
 */
class DialogLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0, defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyle, defStyleRes) {

    private var binding: LayoutBaseDialogBinding
    var positiveButtonListener: (() -> Unit)? = null
    var negativeButtonListener: (() -> Unit)? = null

    init {
        binding = LayoutBaseDialogBinding.inflate(LayoutInflater.from(context), this)

        attrs?.let {

            try {

            } finally {

            }
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        Timber.d("onFinishInflate")
        binding.positiveBtn.setOnClickListener {
            positiveButtonListener?.invoke()
        }

        binding.negativeBtn.setOnClickListener {
            negativeButtonListener?.invoke()
        }

    }

    override fun addView(child: View?, index: Int, params: ViewGroup.LayoutParams?) {
        try {
            binding.lnContent.addView(child, index, params)
        } catch (e: Exception) {
            super.addView(child, index, params)
        }

    }

    fun setNegativeButton(msg: String) {
        if (msg.isEmpty()) {
            binding.negativeBtn.visibility = View.GONE
        } else {
            binding.negativeBtn.text = msg
            binding.negativeBtn.visibility = View.VISIBLE
        }
    }

    fun setPositiveButton(msg: String) {
        if (msg.isEmpty()) {
            binding.positiveBtn.visibility = View.GONE
        } else {
            binding.positiveBtn.text = msg
            binding.positiveBtn.visibility = View.VISIBLE
        }
    }


    fun setImage(resourceID: Int, type: DialogType) {
        binding.iconImg.setImageResource(resourceID)
        when (type) {
            DialogType.NORMAL -> {
                binding.iconImg.imageTintList = ColorStateList.valueOf(
                    ResourcesCompat.getColor(
                        resources, R.color.icon_normal, binding.iconImg.context?.theme
                    )
                )
            }
            DialogType.CONFIRM -> {
                binding.iconImg.imageTintList = ColorStateList.valueOf(
                    ResourcesCompat.getColor(
                        resources, R.color.color_red, binding.iconImg.context?.theme
                    )
                )
            }
            DialogType.ERROR -> {
                binding.iconImg.imageTintList = ColorStateList.valueOf(
                    ResourcesCompat.getColor(
                        resources, R.color.icon_error, binding.iconImg.context?.theme
                    )
                )
            }
            else -> {}
        }
    }

    fun setTitle(msg: String) {
        binding.titleTxt.text = msg
    }
}