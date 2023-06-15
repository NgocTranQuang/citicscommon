package citics.sharing.data.model.tai_san_chi_tiet

import com.citics.valuation.data.model.tai_san_chi_tiet.TypeDetail
import com.sharing.R


class TypeCanHo : TypeDetail {
    override fun getImageTitleResource(): Int {
        return R.drawable.ic_can_ho_white
    }

    override fun getTitle(): Int {
        return R.string.can_ho
    }

    override fun getTitleButton(): Int {
        return R.string.tvdsyc
    }

    override fun getDrawableButton(): Int {
        return R.drawable.ic_folder_plus
    }

    override fun getListTabBarTitle(): MutableList<Int>? {
        return null
    }
}