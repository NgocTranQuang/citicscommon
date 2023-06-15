package citics.sharing.data.model.tai_san_chi_tiet

import com.citics.valuation.data.model.tai_san_chi_tiet.TypeDetail
import com.sharing.R


class TypeNhaDat : TypeDetail {
    override fun getImageTitleResource(): Int {
        return R.drawable.ic_home_white
    }

    override fun getTitle(): Int {
        return R.string.nha_dat
    }

    override fun getTitleButton(): Int {
        return R.string.tvdsyc
    }

    override fun getDrawableButton(): Int {
        return R.drawable.ic_folder_plus
    }

    override fun getListTabBarTitle(): MutableList<Int> {
        return mutableListOf(R.string.vi_tri_tai_san, R.string.ve_thua_dat, R.string.ve_cong_trinh)
    }
}