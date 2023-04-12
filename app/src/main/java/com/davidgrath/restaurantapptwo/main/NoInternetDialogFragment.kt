package com.davidgrath.restaurantapptwo.main

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class NoInternetDialogFragment : DialogFragment() {

    var isShowing = false

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
            .setMessage("No Internet! You cannot use the app till you've reconnected!")
            .setPositiveButton("Close app") { dialog, which ->
                requireActivity().finishAndRemoveTask()
            }
            .setCancelable(false)
        return builder.create()
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        //TODO This should never be called. Testing for now
        Log.d("Dialog", "This should never be called. Testing for now")
        isShowing = false
        requireActivity().finishAndRemoveTask()
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        isShowing = false
        //TODO programmatically dismiss but don't trigger this line
//        requireActivity().finishAndRemoveTask()
    }

    companion object {
        @JvmStatic
        fun newInstance(): NoInternetDialogFragment {
            return NoInternetDialogFragment().apply {

            }
        }
    }
}