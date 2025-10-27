package com.dema.appcalculofarroupilha

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.Html
import android.text.Layout
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.math.BigDecimal

class CalculateFragment : Fragment() {

    private lateinit var txtInputArea : TextInputLayout
    private lateinit var txtEditArea : TextInputEditText
    private lateinit var txtInputVolumePerVolume : TextInputLayout
    private lateinit var txtEditVolumePerVolume : TextInputEditText
    private lateinit var btnCalculate : Button
    private lateinit var txtArea : TextView
    private lateinit var txtVolumePerVolume : TextView
    private lateinit var txtFinalResult : TextView
    private lateinit var btnCalculateArea : FloatingActionButton

    private var currentContext : Context? = null

    private val tag = javaClass.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_calculate, container, false)

        currentContext = requireContext()

        txtInputArea = view.findViewById(R.id.txt_input_area)
        txtEditArea = view.findViewById(R.id.txt_edit_area)
        txtInputVolumePerVolume = view.findViewById(R.id.txt_input_volume_per_volume)
        txtEditVolumePerVolume = view.findViewById(R.id.txt_edit_volume_per_volume)
        btnCalculate = view.findViewById(R.id.btn_calculate)
        txtArea = view.findViewById(R.id.txt_area)
        txtVolumePerVolume = view.findViewById(R.id.txt_volume_per_volume)
        txtFinalResult = view.findViewById(R.id.txt_final_result)
        btnCalculateArea = view.findViewById(R.id.btn_calculate_area)

        btnCalculate.setOnClickListener {
            calculate()
        }

        val txtAreaBuildString = buildString {
            append("<b>Área (m³): </b>")
            append("(Não informado)")
        }
        val txtAreaHtml = Html.fromHtml(txtAreaBuildString, Html.FROM_HTML_MODE_COMPACT)
        txtArea.text = txtAreaHtml

        val txtVolumePerVolumeBuildString = buildString {
            append("<b>mLs por Litro: </b>")
            append("(Não informado)")
        }
        val txtVolumePerVolumeHtml =
            Html.fromHtml(txtVolumePerVolumeBuildString, Html.FROM_HTML_MODE_COMPACT)
        txtVolumePerVolume.text = txtVolumePerVolumeHtml

        val txtFinalResultBuildString = buildString {
            append("<b>Resultado final: </b>")
            append("(Não calculado)")
        }
        val txtFinalResultHtml =
            Html.fromHtml(txtFinalResultBuildString, Html.FROM_HTML_MODE_COMPACT)
        txtFinalResult.text = txtFinalResultHtml

        btnCalculateArea.setOnClickListener {
            showDialogCalculateArea()
        }

        return view
    }

    private fun validation() : Boolean {
        txtInputArea.isErrorEnabled = false
        txtInputVolumePerVolume.isErrorEnabled = false
        var isValid = true

        if (txtEditArea.text.toString().isEmpty()) {
            isValid = false
            txtInputArea.error = "É preciso preencher este campo!"
        }

        if (txtEditVolumePerVolume.text.toString().isEmpty()) {
            isValid = false
            txtInputVolumePerVolume.error = "É preciso preencher este campo!"
        }

        return isValid
    }

    private fun calculate() {
        val validate = validation()

        if (!validate)
            return

        val area = txtEditArea.text.toString()
        val volumePerVolume = txtEditVolumePerVolume.text.toString()

        val txtAreaBuildString = buildString {
            append("<b>Área (m³): </b>")
            append("$area m³")
        }
        val txtAreaHtml = Html.fromHtml(txtAreaBuildString, Html.FROM_HTML_MODE_COMPACT)
        txtArea.text = txtAreaHtml

        val txtVolumePerVolumeBuildString = buildString {
            append("<b>mLs por Litro: </b>")
            append("$volumePerVolume mL/L")
        }
        val txtVolumePerVolumeHtml =
            Html.fromHtml(txtVolumePerVolumeBuildString, Html.FROM_HTML_MODE_COMPACT)
        txtVolumePerVolume.text = txtVolumePerVolumeHtml

        val areaToVolume = area.toDouble() * 1000

        val volumePerVolumeToLiter = volumePerVolume.toDouble() / 1000

        val finalResult = BigDecimal(areaToVolume / volumePerVolumeToLiter)

        Log.v(tag, finalResult.toString())

        val txtFinalResultBuildString = buildString {
            append("<b>Resultado final: </b>")
            append("$finalResult mL")
        }
        val txtFinalResultHtml =
            Html.fromHtml(txtFinalResultBuildString, Html.FROM_HTML_MODE_COMPACT)
        txtFinalResult.text = txtFinalResultHtml
    }

    private fun showDialogCalculateArea() {
        val dialog = Dialog(currentContext!!)
        dialog.setContentView(R.layout.dialog_calculate_area)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)

        val btnClose = dialog.findViewById<ImageView>(R.id.btn_close)
        val txtInputLength = dialog.findViewById<TextInputLayout>(R.id.txt_input_length)
        val txtEditLength = dialog.findViewById<TextInputEditText>(R.id.txt_edit_length)
        val txtInputWidth = dialog.findViewById<TextInputLayout>(R.id.txt_input_width)
        val txtEditWidth = dialog.findViewById<TextInputEditText>(R.id.txt_edit_width)
        val txtInputHeight = dialog.findViewById<TextInputLayout>(R.id.txt_input_height)
        val txtEditHeight = dialog.findViewById<TextInputEditText>(R.id.txt_edit_height)
        val btnCalculateAreaDialog = dialog.findViewById<Button>(R.id.btn_calculate_area_dialog)

        btnClose.setOnClickListener { dialog.cancel() }

        btnCalculateAreaDialog.setOnClickListener {
            txtInputLength.isErrorEnabled = false
            txtInputWidth.isErrorEnabled = false
            txtInputHeight.isErrorEnabled = false
            var isValid = true

            if (txtEditLength.text.toString().isEmpty()) {
                isValid = false
                txtInputLength.error = "É preciso preencher este campo!"
            }

            if (txtEditWidth.text.toString().isEmpty()) {
                isValid = false
                txtInputWidth.error = "É preciso preencher este campo!"
            }

            if (txtEditHeight.text.toString().isEmpty()) {
                isValid = false
                txtInputHeight.error = "É preciso preencher este campo!"
            }

            if (!isValid)
                return@setOnClickListener

            val length = txtEditLength.text.toString()
            val width = txtEditWidth.text.toString()
            val height = txtEditHeight.text.toString()

            val area = length.toDouble() * width.toDouble() * height.toDouble()

            txtEditArea.setText(area.toString())

            dialog.cancel()
        }

        dialog.show()
    }
}