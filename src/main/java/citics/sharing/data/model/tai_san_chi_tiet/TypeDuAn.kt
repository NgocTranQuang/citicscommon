package citics.sharing.data.model.tai_san_chi_tiet

import com.citics.valuation.data.model.tai_san_chi_tiet.TypeDetail
import com.sharing.R


class TypeDuAn : TypeDetail {
    override fun getImageTitleResource(): Int {
        return R.drawable.ic_du_an_white
    }

    override fun getTitle(): Int {
        return R.string.du_an
    }

    override fun getTitleButton(): Int {
        return R.string.tim_can_ho
    }

    override fun getDrawableButton(): Int {
        return R.drawable.ic_tray
    }

    override fun getListTabBarTitle(): MutableList<Int> {
        return mutableListOf(
            R.string.thong_tin_du_an, R.string.chi_tiet_du_an,
            R.string.tien_ich_noi_khu
        )
    }
}