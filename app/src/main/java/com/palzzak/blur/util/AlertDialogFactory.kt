package com.palzzak.blur.util
import android.app.Dialog
import android.app.DialogFragment
import android.app.FragmentManager
import android.os.Bundle
import android.support.v7.app.AlertDialog
import com.palzzak.blur.R


/**
 * Created by yooas on 2018-02-07.
 */
class AlertDialogFactory {
    companion object {
        fun show(fragmentManager: FragmentManager, tag: String) {
            when (tag) {
                Constants.DIALOG_TAG_QUIT -> QuitDialog().show(fragmentManager, tag)
            }
        }
    }
}

internal class QuitDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(activity).setTitle(R.string.quit_quiz_title)
                .setMessage(R.string.quit_quiz_message)
                .setPositiveButton(R.string.yes, { dialog, id ->
                    activity.onBackPressed()
                    activity.finish() })
                .setNegativeButton(R.string.no, null)
                .create()
    }
}