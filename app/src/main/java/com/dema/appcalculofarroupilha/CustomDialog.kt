package com.dema.appcalculofarroupilha

import android.app.Dialog
import android.content.Context
import android.view.ViewGroup
import android.widget.ImageView

open class CustomDialog(context : Context) {
    private var context : Context? = null

    init {
        this.context = context
    }

    fun showDialogCalculator() {

    }

    fun showDialogInfoMeasure() {
        if (context != null) {
            val dialog = Dialog(context!!)
            dialog.setContentView(R.layout.dialog_measures)
            dialog.window?.setLayout(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)

            val imgBtnExit = dialog.findViewById<ImageView>(R.id.img_btn_exit)

            imgBtnExit.setOnClickListener { dialog.cancel() }

            dialog.show()
        }
    }

}