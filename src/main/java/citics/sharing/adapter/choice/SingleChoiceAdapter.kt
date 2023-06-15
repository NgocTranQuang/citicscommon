package citics.sharing.adapter.choice

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import citics.sharing.adapter.base.BaseAdapter
import citics.sharing.data.model.others.ChooserItem
import citics.sharing.extension.applyCiticsStyle
import com.sharing.databinding.LayoutRowSingleChoiceBinding


class SingleChoiceAdapter(
    var context: Context,
    var selected: String?,
    var list: MutableList<ChooserItem>,
    var onChange: () -> Unit
) : BaseAdapter<LayoutRowSingleChoiceBinding, ChooserItem>(context, list) {
    private var lastCheckedPosition = -1

    init {
        lastCheckedPosition = list.indexOfFirst { it.name == selected }
    }

    override val bindingInflater: (LayoutInflater, ViewGroup, Boolean) -> LayoutRowSingleChoiceBinding
        get() = LayoutRowSingleChoiceBinding::inflate

    override fun ChooserItem.handleItem(binding: LayoutRowSingleChoiceBinding?, position: Int) {
        binding?.btnRadio?.setOnCheckedChangeListener(null);
        binding?.btnRadio?.text = this.name
        binding?.btnRadio?.isChecked = lastCheckedPosition == position
        binding?.btnRadio?.setOnCheckedChangeListener { buttonView, isChecked ->
            val copyOfLastCheckedPosition = lastCheckedPosition
            lastCheckedPosition = position
            notifyItemChanged(copyOfLastCheckedPosition)
            notifyItemChanged(lastCheckedPosition)
            onChange.invoke()
        }
        binding?.btnRadio?.applyCiticsStyle()
    }

    fun getSelected(): ChooserItem? {
        return list.getOrNull(lastCheckedPosition)
    }
}