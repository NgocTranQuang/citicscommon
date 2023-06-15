package citics.sharing.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import citics.sharing.data.model.others.ChooserItem
import citics.sharing.data.model.others.SelectorItem
import citics.sharing.extension.loadByName
import com.sharing.R
import com.sharing.databinding.LayoutMultiChoiceBinding
import timber.log.Timber

/**
 * Created by ChinhQT on 25/10/2022.
 */
class LayoutMultiChoice @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0, defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyle, defStyleRes) {

    private var binding: LayoutMultiChoiceBinding
    private var onChange: ((Boolean) -> Unit)? = null

    init {
        binding = LayoutMultiChoiceBinding.inflate(LayoutInflater.from(context), this)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(
                it, R.styleable.LayoutMultiChoice, 0, 0
            )

            try {
                val image =
                    typedArray.getDrawable(R.styleable.LayoutMultiChoice_layout_multi_choice_icon)
                image?.let {
                    binding.imgSymbol.setImageDrawable(image)
                }
                val title =
                    typedArray.getString(R.styleable.LayoutMultiChoice_layout_multi_choice_title)

                title?.let {
                    binding.tvTitle.text = title
                }

            } finally {
                typedArray.recycle()
            }
        }
    }

    fun setData(showImage: Boolean, data: ChooserItem) {
        binding.sw.setOnCheckedChangeListener(null)
        binding.tvTitle.text = data.name
        binding.sw.isChecked = data.isSelected
        if (showImage) {
            binding.imgSymbol.visibility = View.VISIBLE
            binding.imgSymbol.loadByName("ic_mdsdd_" + data.id.lowercase())
        } else {
            binding.imgSymbol.visibility = View.GONE
        }
        binding.sw.setOnCheckedChangeListener { buttonView, isChecked ->
            onChange?.invoke(isChecked)
        }
    }

    fun setData(showImage: Boolean, data: SelectorItem) {
        binding.sw.setOnCheckedChangeListener(null)
        binding.tvTitle.text = data.name
        binding.sw.isChecked = data.isSelected
        if (showImage) {
            binding.imgSymbol.visibility = View.VISIBLE
            binding.imgSymbol.loadByName("ic_mdsdd_" + data.id.lowercase())
        } else {
            binding.imgSymbol.visibility = View.GONE
        }
        binding.sw.setOnCheckedChangeListener { buttonView, isChecked ->
            onChange?.invoke(isChecked)
        }
    }

    fun setOnChangeListener(onChange: (Boolean) -> Unit) {
        this.onChange = onChange
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        Timber.d("onFinishInflate")
    }

}