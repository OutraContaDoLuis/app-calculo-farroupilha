package com.dema.appcalculofarroupilha

import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.flagging.Flags
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.lang.reflect.Type

class MainActivity : AppCompatActivity() {

    private lateinit var cardCalculateVolumePerArea : CardView
    private lateinit var expandedCalculateVolumePerArea : LinearLayout
    private lateinit var txtInputVolumeCalculate1 : TextInputLayout
    private lateinit var txtEditVolumeCalculate1 : TextInputEditText
    private lateinit var txtInputAreaCalculate1 : TextInputLayout
    private lateinit var txtEditAreaCalculate1 : TextInputEditText
    private lateinit var btnCalculate1 : Button
    private lateinit var txtVolume : TextView
    private lateinit var txtArea : TextView
    private lateinit var txtVolumePerArea : TextView

    private lateinit var cardCalculateVolumePerArea2 : CardView
    private lateinit var expandedCalculateVolumePerArea2 : LinearLayout

    private lateinit var cardCalculateQuantityContainers : CardView
    private lateinit var expandedCalculateQuantityContainers : LinearLayout

    private var isExpandedCardCalculateVolumePerArea = false
    private var isExpandedCardCalculateVolumePerArea2 = false
    private var isExpandedCalculateQuantityContainers = false

    private val tag = javaClass.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        cardCalculateVolumePerArea = findViewById(R.id.card_calculate_volume_per_area)
        expandedCalculateVolumePerArea = findViewById(R.id.expanded_calculate_volume_per_area)
        txtInputVolumeCalculate1 = findViewById(R.id.txt_input_volume_calculate_1)
        txtEditVolumeCalculate1 = findViewById(R.id.txt_edit_volume_calculate_1)
        txtInputAreaCalculate1 = findViewById(R.id.txt_input_area_calculate_1)
        txtEditAreaCalculate1 = findViewById(R.id.txt_edit_area_calculate_1)
        btnCalculate1 = findViewById(R.id.btn_calculate_1)
        txtVolume = findViewById(R.id.txt_volume)
        txtArea = findViewById(R.id.txt_area)
        txtVolumePerArea = findViewById(R.id.txt_volume_per_area)

        cardCalculateVolumePerArea2 = findViewById(R.id.card_calculate_volume_per_area_2)
        expandedCalculateVolumePerArea2 = findViewById(R.id.expanded_calculate_volume_per_area_2)

        cardCalculateQuantityContainers = findViewById(R.id.card_calculate_quantity_containers)
        expandedCalculateQuantityContainers =
            findViewById(R.id.expanded_calculate_quantity_containers)
    }

    private fun setTheErrorInputLayout(
        inputLayout : TextInputLayout,
        typeValidate : TypeValidate,
        itsVolumeInput : Boolean) {
        when (typeValidate) {
            TypeValidate.VALID -> {}
            TypeValidate.INVALID_TEXT_FORMAT -> {
                inputLayout.error = "É preciso digitar no formato (número) (medida)!"
            }
            TypeValidate.MISSING_NUMBER -> {
                inputLayout.error = "Digite um número!"
            }
            TypeValidate.INVALID_NUMBER -> {
                inputLayout.error = "Digite um número válido!"
            }
            TypeValidate.TEXT_EMPTY -> {
                inputLayout.error = "É preciso digitar algum valor nesse campo!"
            }
            TypeValidate.MISSING_MEASURE -> {
                inputLayout.error = if (itsVolumeInput) {
                    "É preciso digitar uma medida (kg/L/mL)!"
                } else {
                    "É preciso digitar uma medida (m²/cm²/m³/cm³)!"
                }
            }
        }
    }

    private fun expandedCalculateVolumePerArea() {
        isExpandedCardCalculateVolumePerArea = !isExpandedCardCalculateVolumePerArea

        if (isExpandedCardCalculateVolumePerArea) {
            expandedCalculateVolumePerArea.visibility = View.VISIBLE
        } else {
            expandedCalculateVolumePerArea.visibility = View.GONE
        }
    }

    private fun validateToCalculateVolumePerArea() : Boolean {
        txtInputVolumeCalculate1.isErrorEnabled = false
        txtInputAreaCalculate1.isErrorEnabled = false

        val volume = txtEditVolumeCalculate1.text.toString()
        val area = txtEditAreaCalculate1.text.toString()

        val validationFormsVolumeArea = ValidationFormsVolumeArea()

        val volumeValidationModel = validationFormsVolumeArea.validateFormVolume(volume)
        val areaValidationModel = validationFormsVolumeArea.validateFormArea(area)

        setTheErrorInputLayout(txtInputVolumeCalculate1, volumeValidationModel.typeValidate, true)
        setTheErrorInputLayout(txtInputAreaCalculate1, areaValidationModel.typeValidate, false)

        val formIsValid = volumeValidationModel.isValid && areaValidationModel.isValid

        return formIsValid
    }

    private fun calculateVolumePerArea() {
        val isValid = validateToCalculateVolumePerArea()

        if (!isValid) {
            return
        }

        val volumeEditText = txtEditVolumeCalculate1.text.toString()
        val volumeText = volumeEditText.split(" ")
        val volume = volumeText[0].toDouble()

        val areaEditText = txtEditAreaCalculate1.text.toString()
        val areaText = areaEditText.split(" ")
        val area = areaText[0].toDouble()

        val volumePerArea = volume / area

        val volumeHtml = buildString {
            append("<b>Volume: </b>")
            append(volumeEditText)
        }
        txtVolume.text = Html.fromHtml(volumeHtml, Html.FROM_HTML_MODE_LEGACY)

        val areaHtml = buildString {
            append("<b>Área: </b>")
            append(areaEditText)
        }
        txtArea.text = Html.fromHtml(areaHtml, Html.FROM_HTML_MODE_LEGACY)

        val resultHtml = buildString {
            append("<b>Resultado: </b>")
            append("$volumePerArea ${volumeText[1]}/${areaText[1]}")
        }
        txtVolumePerArea.text = Html.fromHtml(resultHtml, Html.FROM_HTML_MODE_LEGACY)
    }

    private fun expandedCalculateVolumePerArea2() {
        isExpandedCardCalculateVolumePerArea2 = !isExpandedCardCalculateVolumePerArea2

        if (isExpandedCardCalculateVolumePerArea2) {
            expandedCalculateVolumePerArea2.visibility = View.VISIBLE
        } else {
            expandedCalculateVolumePerArea2.visibility = View.GONE
        }
    }

    private fun expandedCalculateQuantityContainers() {
        isExpandedCalculateQuantityContainers = !isExpandedCalculateQuantityContainers

        if (isExpandedCalculateQuantityContainers) {
            expandedCalculateQuantityContainers.visibility = View.VISIBLE
        } else {
            expandedCalculateQuantityContainers.visibility = View.GONE
        }
    }

    override fun onStart() {
        super.onStart()

        cardCalculateVolumePerArea.setOnClickListener { expandedCalculateVolumePerArea() }

        btnCalculate1.setOnClickListener { calculateVolumePerArea() }

        val volumeHtml = buildString {
            append("<b>Volume: </b> (não calculado).")
        }
        txtVolume.text = Html.fromHtml(volumeHtml, Html.FROM_HTML_MODE_LEGACY)

        val areaHtml = buildString {
            append("<b>Área: </b> (não calculado).")
        }
        txtArea.text = Html.fromHtml(areaHtml, Html.FROM_HTML_MODE_LEGACY)

        val resultHtml = buildString {
            append("<b>Resultado: </b> (não calculado).")
        }
        txtVolumePerArea.text = Html.fromHtml(resultHtml, Html.FROM_HTML_MODE_LEGACY)

        cardCalculateVolumePerArea2.setOnClickListener { expandedCalculateVolumePerArea2() }

        cardCalculateQuantityContainers.setOnClickListener { expandedCalculateQuantityContainers() }

        expandedCalculateVolumePerArea.visibility = View.GONE
        expandedCalculateVolumePerArea2.visibility = View.GONE
        expandedCalculateQuantityContainers.visibility = View.GONE
    }
}