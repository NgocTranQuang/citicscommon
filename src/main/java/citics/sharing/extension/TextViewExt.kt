package citics.sharing.extension

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.citics.cbank.R


fun TextView.makeLinks(vararg links: Pair<String, () -> Unit>) {
    val spannableString = SpannableString(text)
    var startIndexOfLink = -1
    for (link in links) {
        val clickableSpan = object : ClickableSpan() {
            override fun updateDrawState(textPaint: TextPaint) {
                // use this to change the link color
//                textPaint.color = textPaint.linkColor
                textPaint.color = (context.resources.getColor(R.color.stfImageColor, null))

            }

            override fun onClick(view: View) {
                Selection.setSelection((view as TextView).text as Spannable, 0)
                view.invalidate()
                link.second()
            }
        }
        startIndexOfLink = text.toString().indexOf(link.first, startIndexOfLink + 1)
        //      if(startIndexOfLink == -1) continue // todo if you want to verify your texts contains links text
        spannableString.setSpan(
            clickableSpan,
            startIndexOfLink,
            startIndexOfLink + link.first.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
    this.movementMethod =
        LinkMovementMethod.getInstance() // without LinkMovementMethod, link can not click
    this.setText(spannableString, TextView.BufferType.SPANNABLE)
}

fun TextView.setHtml(value: String?) {
    text = Html.fromHtml(value, Html.FROM_HTML_MODE_LEGACY)
}
//
//@SuppressLint("SetTextI18n")
//fun TextViewDrawableSize.setPercent(value: Float?, iconUp: Int? = null, iconDown: Int? = null) {
//    val newValue: Float = value ?: 0.0f
//    this.text = newValue.toShow() + "%"
//    var color: Int = 0
//    var icon: Int = 0
//    if (newValue < 0) {
//        color = R.color.color_red
//        icon = iconDown ?: R.drawable.ic_arrow_down
//    } else {
//        color = R.color.color_xanh_la_cay
//        icon = iconUp ?: R.drawable.ic_arrow_up
//    }
//    setDrawableStartTextview(icon)
//    this.setTextColor(context.getColor(color))
//    for (drawable in compoundDrawables) {
//        if (drawable != null) {
//            drawable.colorFilter =
//                PorterDuffColorFilter(
//                    ContextCompat.getColor(context, color),
//                    PorterDuff.Mode.SRC_IN
//                )
//        }
//    }
//}

fun TextView.setTextViewDrawableColor(color: Int) {
    for (drawable in compoundDrawables) {
        if (drawable != null) {
            drawable.colorFilter =
                PorterDuffColorFilter(
                    ContextCompat.getColor(context, color),
                    PorterDuff.Mode.SRC_IN
                )
        }
    }
}