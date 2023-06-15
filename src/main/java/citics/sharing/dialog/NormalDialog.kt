package citics.sharing.dialog

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.Gravity
import android.view.View
import androidx.annotation.StringRes
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import citics.sharing.extension.setDefaultWindowTheme
import citics.sharing.extension.setHtml
import citics.sharing.utils.DialogType
import com.sharing.databinding.LayoutDialogCiticsBinding
import timber.log.Timber

/**
 * Created by ChinhQT on 07/11/2022.
 */
class NormalDialog : BaseDialog<LayoutDialogCiticsBinding>(LayoutDialogCiticsBinding::inflate) {

    var isType = DialogType.NORMAL
    private var iconResource: Int = 0
    var title: CharSequence? = null
    private var mMessage: CharSequence? = null

    private var mPositive: CharSequence = ""
    var mPositiveButtonListener: DialogInterface.OnClickListener? = null
    private var mNegative: CharSequence = ""
    var mNegativeButtonListener: DialogInterface.OnClickListener? = null

    var gravity: Int = Gravity.CENTER

    companion object {
        const val TAG = "NormalDialog"
    }

    override fun onStart() {
        super.onStart()
        dialog?.setDefaultWindowTheme()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Timber.d("onViewCreated")

        setImage(iconResource, isType)
        setTitle(title.toString())
        setDescription(mMessage.toString())
        setPositiveButton(mPositive.toString())
        setNegativeButton(mNegative.toString())
        setStart(gravity)
        isCancelable = false
        binding?.root?.negativeButtonListener = {
            mNegativeButtonListener?.onClick(this.dialog, DialogInterface.BUTTON_NEGATIVE)
            dismiss()
        }
        binding?.root?.positiveButtonListener = {
            mPositiveButtonListener?.onClick(this.dialog, DialogInterface.BUTTON_POSITIVE)
            dismiss()
        }
    }

//    override fun getTheme(): Int = R.style.CiticsDialogTheme

    private fun setTitle(msg: String) {
        title = msg
        binding?.root?.setTitle(msg)
    }

    private fun setImage(resourceID: Int, isType: DialogType = DialogType.NORMAL) {
        this.iconResource = resourceID
        this.isType = isType

        binding?.root?.setImage(resourceID, isType)

    }

    private fun setStart(gravity: Int) {
        this.gravity = gravity
        binding?.descriptionTxt?.gravity = gravity
    }

    private fun setDescription(msg: String) {
        mMessage = msg
        if (msg.contains(">")) {
            binding?.descriptionTxt?.setHtml(msg)
        } else {
            binding?.descriptionTxt?.text = msg
        }
    }

    private fun setPositiveButton(msg: String) {
        mPositive = msg
        binding?.root?.setPositiveButton(msg)
    }

    private fun setNegativeButton(msg: String) {
        mNegative = msg
        binding?.root?.setNegativeButton(msg)

    }

    class Builder(val context: Context) {

        var dialog = NormalDialog()

        fun setTitle(text: CharSequence): Builder {
            dialog.setTitle(text.toString())
            return this
        }

        fun setTitle(@StringRes textId: Int): Builder {
            dialog.setTitle(context.getString(textId))
            return this
        }

        fun setMessage(text: CharSequence): Builder {
            dialog.setDescription(text.toString())
            return this
        }

        fun setMessage(text: String): Builder {
            dialog.setDescription(text)
            return this
        }

        fun setMessage(@StringRes textId: Int): Builder {
            dialog.setDescription(context.getString(textId))
            return this
        }

        fun setImage(
            resourceID: Int, isType: DialogType = DialogType.NORMAL
        ): Builder {
            dialog.setImage(resourceID, isType)
            return this
        }

        fun setPositiveButton(
            @StringRes textId: Int, listener: (DialogInterface, Int) -> Unit
        ): Builder {
            dialog.setPositiveButton(context.getString(textId))
            dialog.mPositiveButtonListener = DialogInterface.OnClickListener(listener)
            return this
        }

        fun setNegativeButton(
            @StringRes textId: Int, listener: (DialogInterface, Int) -> Unit
        ): Builder {
            dialog.setNegativeButton(context.getString(textId))
            dialog.mNegativeButtonListener = DialogInterface.OnClickListener(listener)
            return this
        }

        fun setStart(): Builder {
            dialog.setStart(Gravity.START)
            return this
        }

        fun show(manager: FragmentManager): NormalDialog {
            dialog.show(manager, TAG)
            return dialog
        }

        fun show(transaction: FragmentTransaction): NormalDialog {
            dialog.show(transaction, TAG)
            return dialog
        }
    }

}