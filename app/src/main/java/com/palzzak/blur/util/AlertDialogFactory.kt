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
                Constants.DIALOG_QUIZ_TAG_QUIT-> QuitQuizDialog().show(fragmentManager, tag)
                Constants.DIALOG_QUESTION_TAG_QUIT -> QuitQuestionDialog().show(fragmentManager, tag)
                Constants.DIALOG_CHAT_TAG_QUIT -> QuitChatDialog().show(fragmentManager, tag)
            }

        }
    }
}

internal class QuitQuizDialog: DialogFragment() {
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

internal class QuitQuestionDialog: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(activity).setTitle(R.string.quit_question_title)
                .setMessage(R.string.quit_question_message)
                .setPositiveButton(R.string.yes, { dialog, id ->
                    activity.onBackPressed()
                    activity.finish() })
                .setNegativeButton(R.string.no, null)
                .create()
    }
}

internal class QuitChatDialog: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(activity).setTitle(R.string.quit_question_title)
                .setMessage(R.string.quit_chat_message)
                .setPositiveButton(R.string.yes, { dialog, id ->
                    activity.onBackPressed()
                    activity.finish() })
                .setNegativeButton(R.string.no, null)
                .create()
    }
}