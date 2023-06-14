package citics.sharing.customview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.citics.cbank.databinding.LayoutChipCustomBinding

/**
 * Created by ChinhQT on 25/10/2022.
 */
class CustomChipLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0, defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyle, defStyleRes) {

    private var binding: LayoutChipCustomBinding


    init {
        binding = LayoutChipCustomBinding.inflate(LayoutInflater.from(context), this)
    }

    fun setValue(value: String?, chipType: ChipType) {
        if (chipType == ChipType.NORMAL) {
            binding.tvChip.visibility = View.GONE
        } else if (chipType == ChipType.MATCH_PARENT) {
            binding.chip.visibility = View.GONE
        }
        binding.chip.text = value
        binding.tvChip.text = value
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

    }

    enum class ChipType {
        NORMAL,
        MATCH_PARENT
    }

}