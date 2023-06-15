package citics.sharing.utils

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentManager
import citics.sharing.dialog.NormalDialog
import com.sharing.R


object DialogUtils {
    fun showDialogConfirm(
        context: Context,
        fm: FragmentManager,
        title: Int,
        message: String,
        textButton: Int,
        textNegative: Int,
        onOk: () -> Unit,
        onNega: () -> Unit
    ) {
        NormalDialog.Builder(context).setImage(R.drawable.ic_check_circle_o)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(textButton) { dialog, int ->
                onOk.invoke()
                dialog.dismiss()
            }.setNegativeButton(textNegative) { dialog, int ->
                onNega.invoke()
                dialog.dismiss()
            }.show(fm)
    }

    fun showAlertDialog(
        context: Context,
        title: String?,
        message: String?,
        onCancel: (() -> Unit)?,
        onOK: () -> Unit
    ) {
        val builder1: AlertDialog.Builder = AlertDialog.Builder(context)
        builder1.setTitle(title)
        builder1.setMessage(message)
        builder1.setCancelable(false)

        builder1.setPositiveButton(
            context.getString(R.string.dong_y),
            DialogInterface.OnClickListener { dialog, id ->
                onOK.invoke()
                dialog.cancel()
            })
        onCancel?.let {
            builder1.setNegativeButton(
                context.getString(R.string.tu_choi),
                DialogInterface.OnClickListener { dialog, id ->
                    onCancel.invoke()
                    dialog.cancel()
                })
        }

        val alert11: AlertDialog = builder1.create()
        alert11.show()
    }

    fun showDialogHetCPoint(
        context: Context,
        fm: FragmentManager,
    ) {
        NormalDialog.Builder(context).setImage(R.drawable.ic_search)
            .setTitle(R.string.title_dialog_hetcpoint)
            .setMessage(context.getString(R.string.des_dialog_het_cpoint))
            .setPositiveButton(R.string.text_button_dialog_het_cpoint) { dialog, int ->
                dialog.dismiss()
//                context.startActivity(Intent(context, PhuongThucNhanCpointActivity::class.java))
            }.show(fm)

    }

}