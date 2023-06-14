package citics.sharing.customview

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.citics.cbank.R
import com.citics.cbank.databinding.LayoutImageSliderBinding
import com.glide.slider.library.slidertypes.BaseSliderView
import com.glide.slider.library.slidertypes.TextSliderView
import com.glide.slider.library.tricks.ViewPagerEx


/**
 * Created by ChinhQT on 09/10/2022.
 */
class ImageSliderLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0, defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyle, defStyleRes) {

    private var binding: LayoutImageSliderBinding
    private var totalImages = 0

    init {
        binding = LayoutImageSliderBinding.inflate(LayoutInflater.from(context), this)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(
                it, R.styleable.ImageSliderLayout, 0, 0
            )

            try {
                val isShowText =
                    typedArray.getBoolean(R.styleable.ImageSliderLayout_isShowText, false)

                binding.tvCount.visibility = if (isShowText) View.VISIBLE else View.GONE
            } finally {
                typedArray.recycle()
            }
        }
    }

    fun setData(listImage: MutableList<String>) {
        totalImages = listImage.size
        for (name in listImage) {
            val textSliderView = TextSliderView(context)
            // initialize a SliderLayout
            textSliderView
                .image(name)
            binding.imageSlider.addSlider(textSliderView)
        }
        binding.imageSlider.stopAutoCycle()
    }

    fun stopAutoCycle() {
        binding.imageSlider.stopAutoCycle()
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        binding.imageSlider.addOnPageChangeListener(object : ViewPagerEx.OnPageChangeListener {

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            override fun onPageSelected(position: Int) {
                binding.tvCount.text =
                    context.getString(R.string.image_slide, position, totalImages)
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })
    }

}