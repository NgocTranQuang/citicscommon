package citics.sharing.utils.biometric

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import com.google.android.material.card.MaterialCardView
import android.widget.TextView
import android.view.ViewGroup
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import com.sharing.R

class BiometricDialogV23 : DialogFragment, View.OnClickListener {
    private var mContext: Context?=null
    private var btnCancel: MaterialCardView? = null
    private var imgLogo: ImageView? = null
    private var itemTitle: TextView? = null
    private var itemSubtitle: TextView? = null
    private var biometricCallback: BiometricCallback? = null

    constructor(context: Context) {
        this.mContext = context.applicationContext
    }

    constructor(context: Context, biometricCallback: BiometricCallback?) {
        this.mContext = context.applicationContext
        this.biometricCallback = biometricCallback
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            dialog.window!!.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setCanceledOnTouchOutside(true)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottomsheet_biometric_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDialogView(view)
    }

    @SuppressLint("SetTextI18n")
    private fun setDialogView(view: View) {
        btnCancel = view.findViewById(R.id.btn_cancel)
        btnCancel?.setOnClickListener(this)
        imgLogo = view.findViewById(R.id.img_logo)
        itemTitle = view.findViewById(R.id.item_title)
        itemTitle?.setText(
            getContext()?.getString(R.string.fingerPrinter_for) + " " + getContext()?.getString(
                R.string.app_name
            )
        )
        itemSubtitle = view.findViewById(R.id.item_subtitle)
        isCancelable = true
        view.findViewById<View>(R.id.root).setOnClickListener(this)
        view.findViewById<View>(R.id.imgClose).setOnClickListener(this)
    }

    fun setTitle(title: String?) {
        itemTitle!!.text = title
    }

    fun setLogo(resource: Int) {
        imgLogo!!.setImageResource(resource)
    }

    fun setBackgroundButton(resource: Int) {
        btnCancel!!.setBackgroundResource(resource)
    }

    fun updateStatus(status: String?) {
//        itemStatus.setText(status);
    }

    fun setSubtitle(subtitle: String?) {
        itemSubtitle!!.text = subtitle
    }

    fun setDescription(description: String?) {
//        itemDescription.setText(description);
    }

    fun setButtonText(negativeButtonText: String?) {
//        btnCancel.setText(negativeButtonText);
    }

//    private fun updateLogo() {
//        try {
//            val drawable = getContext()?.packageManager?.getApplicationIcon(context?.packageName)
//            imgLogo!!.setImageDrawable(drawable)
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//    }

    override fun onClick(view: View) {
        dismiss()
        biometricCallback!!.onAuthenticationCancelled()
    }
}