package citics.sharing.customview

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import citics.sharing.customview.dfAssetData.nhadat.edit.BaseLayoutDF
import citics.sharing.data.model.others.ChooserItem
import citics.sharing.data.model.others.MultiChoiceData
import citics.sharing.data.model.others.SelectorItem
import citics.sharing.extension.setListChip
import com.sharing.R
import com.sharing.databinding.LayoutChooserWithChipBinding

/**
 * Created by ChinhQT on 25/10/2022.
 */
class ChooserWithChipsLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0, defStyleRes: Int = 0
) : BaseLayoutDF(context, attrs, defStyle, defStyleRes) {

    private var binding: LayoutChooserWithChipBinding
    private var lstChoice: List<SelectorItem> = listOf()
    private var lstChoiceChoosed: List<SelectorItem> = listOf()

    init {
        binding = LayoutChooserWithChipBinding.inflate(LayoutInflater.from(context), this)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(
                it, R.styleable.ChooserWithChipsLayout, 0, 0
            )

            try {
                val icon = typedArray.getDrawable(
                    R.styleable.ChooserWithChipsLayout_chooser_chip_icon
                )

                val resourceId =
                    typedArray.getResourceId(
                        R.styleable.ChooserWithChipsLayout_chooser_chip_title,
                        0
                    )
                val hasOther = typedArray.getBoolean(
                    R.styleable.ChooserWithChipsLayout_chooser_chip_has_other,
                    false
                )

                binAttrsInput(icon, resourceId, hasOther)

            } finally {
                typedArray.recycle()
            }
        }
    }

    fun binAttrsInput(icon: Drawable?, resourceId: Int, hasOrder: Boolean) {
        val title = context.getString(resourceId)
        val hint = context.getString(R.string.chon) + " ${title.lowercase()}"
        val titleChoosed =
            title + " " + context.getString(R.string.da_chon).lowercase()
        binding.cChooser.bindData(title ?: "", icon != null, icon, "", hint, 1)
        binding.tvTitleSelected.text = titleChoosed
        binding.cChooser.onClickListener = {

            openMultiChoiceActivity(
                MultiChoiceData(
                    title = resourceId,
                    lstData = lstChoice.map {
                        ChooserItem(
                            id = it.id,
                            name = it.name,
                            isCustomData = it.isCustomData
                        ).apply {
                            isSelected = it.isSelected
                        }
                    },
                    lstChoiceChoosed.map { it.name }.toMutableList(),
                    hasOrder
                )
            )
        }
    }

//    fun setChoice(choiceManager: ChoiceManager?) {
//        this.choiceManager = choiceManager
//    }

    fun setListChoice(list: List<SelectorItem>) {
        lstChoice = list
    }

    fun setListSelected(list: List<SelectorItem>) {
        lstChoiceChoosed = list
        binding.chipGroup.setListChip(lstChoiceChoosed.toMutableList().map { it.name }
            .toMutableList())
    }

    fun setState(pass: Boolean) {
        binding.cChooser.setState(pass)
    }


}