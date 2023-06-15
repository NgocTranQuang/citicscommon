package citics.sharing.customview

import android.content.Context
import android.content.res.TypedArray
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.sharing.R
import timber.log.Timber


class TextViewDrawableSize : androidx.appcompat.widget.AppCompatTextView {
    private var drawableSize: Int? = null
    private var drawableColor: Int? = null

    constructor(context: Context) : this(context, null) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {

        attrs?.let {
           val typedArray =
                context.obtainStyledAttributes(attrs, R.styleable.TextViewDrawableSize)
            try {
                val drawableStart =
                    typedArray.getDrawable(R.styleable.TextViewDrawableSize_android_drawableStart)
                drawableSize =
                    typedArray.getDimensionPixelSize(
                        R.styleable.TextViewDrawableSize_drawableSize,
                        -1
                    )
                drawableColor =
                    typedArray.getColor(
                        R.styleable.TextViewDrawableSize_drawableSizeTint, -1
                    )

                Timber.d("drawableColor ${drawableColor}")
                setDrawableStartTextview(drawableStart)
            } finally {
                typedArray.recycle()
            }
        }

    }

    fun setDrawableStartTextview(drawableStart: Drawable?) {
        drawableStart?.let { drawable ->
            val wrappedDrawable = WrappedDrawable(drawable)
            wrappedDrawable.let { wrappedDr ->
                if (drawableColor != -1) {
                    drawableColor?.let {
                        wrappedDr.colorFilter = PorterDuffColorFilter(
                            it,
                            PorterDuff.Mode.SRC_IN
                        )
                    }
                }
                val bitmapOld = drawable.toBitmap()
                val widthNewDrawable =
                    if (drawableSize != -1 && drawableSize != null) drawableSize else textSize.toInt()
                val heightNewDrawable =
                    (widthNewDrawable ?: 0) * bitmapOld.height / bitmapOld.width
                wrappedDr.setBounds(0, 0, (widthNewDrawable ?: 0), heightNewDrawable)
                setCompoundDrawablesWithIntrinsicBounds(wrappedDr, null, null, null)
            }
        }
    }

    fun setDrawableStartTextview(drawableStart: Int?) {
        drawableStart?.let { drawable ->
            val newDrawable = ContextCompat.getDrawable(context, drawableStart)
            setDrawableStartTextview(newDrawable)
        }
    }
}

