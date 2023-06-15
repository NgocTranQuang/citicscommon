package com.citics.valuation.data.model.tai_san_chi_tiet

interface TypeDetail {
    fun getImageTitleResource(): Int
    fun getTitle(): Int
    fun getTitleButton(): Int
    fun getDrawableButton(): Int
    fun getListTabBarTitle(): MutableList<Int>?
}