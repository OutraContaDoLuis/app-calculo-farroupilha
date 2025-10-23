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
    private lateinit var txtInputCapacityContainer : TextInputLayout
    private lateinit var txtEditCapacityContainer : TextInputEditText
    private lateinit var txtInputVolumePerVolume : TextInputLayout
    private lateinit var txtEditVolumePerVolume : TextInputEditText
    private lateinit var txtInputAreaCalculate2 : TextInputLayout
    private lateinit var txtEditAreaCalculate2 : TextInputEditText
    private lateinit var btnCalculate2 : Button
    private lateinit var txtCapacityContainer : TextView
    private lateinit var txtVolumePerVolume : TextView
    private lateinit var txtArea2 : TextView
    private lateinit var txtResults2 : TextView

    private lateinit var cardCalculateQuantityContainers : CardView
    private lateinit var expandedCalculateQuantityContainers : LinearLayout
    private lateinit var txtInputCapacityContainer3 : TextInputLayout
    private lateinit var txtEditCapacityContainer3 : TextInputEditText
    private lateinit var txtInputQuantityVolumePerArea : TextInputLayout
    private lateinit var txtEditQuantityVolumePerArea : TextInputEditText
    private lateinit var txtInputTotalArea3 : TextInputLayout
    private lateinit var txtEditTotalArea3 : TextInputEditText
    private lateinit var btnCalculate3 : Button
    private lateinit var txtCapacityContainer3 : TextView
    private lateinit var txtProductVolumePerArea : TextView
    private lateinit var txtArea3 : TextView
    private lateinit var txtResults3 : TextView

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
        txtInputCapacityContainer = findViewById(R.id.txt_input_capacity_container)
        txtEditCapacityContainer = findViewById(R.id.txt_edit_capacity_container)
        txtInputVolumePerVolume = findViewById(R.id.txt_input_volume_per_volume)
        txtEditVolumePerVolume = findViewById(R.id.txt_edit_volume_per_volume)
        txtInputAreaCalculate2 = findViewById(R.id.txt_input_area_calculate_2)
        txtEditAreaCalculate2 = findViewById(R.id.txt_edit_area_calculate_2)
        btnCalculate2 = findViewById(R.id.btn_calculate_2)
        txtCapacityContainer = findViewById(R.id.txt_capacity_container)
        txtVolumePerVolume = findViewById(R.id.txt_volume_per_volume)
        txtArea2 = findViewById(R.id.txt_area_2)
        txtResults2 = findViewById(R.id.txt_results_2)

        cardCalculateQuantityContainers = findViewById(R.id.card_calculate_quantity_containers)
        expandedCalculateQuantityContainers =
            findViewById(R.id.expanded_calculate_quantity_containers)
        txtInputCapacityContainer3 = findViewById(R.id.txt_input_capacity_container_2)
        txtEditCapacityContainer3 = findViewById(R.id.txt_edit_capacity_container_2)
        txtInputQuantityVolumePerArea = findViewById(R.id.txt_input_quantity_volume_per_area)
        txtEditQuantityVolumePerArea = findViewById(R.id.txt_edit_quantity_volume_per_area)
        txtInputTotalArea3 = findViewById(R.id.txt_input_total_area_3)
        txtEditTotalArea3 = findViewById(R.id.txt_edit_total_area_3)
        btnCalculate3 = findViewById(R.id.btn_calculate_3)
        txtCapacityContainer3 = findViewById(R.id.txt_capacity_container_2)
        txtProductVolumePerArea = findViewById(R.id.txt_product_volume_per_area)
        txtArea3 = findViewById(R.id.txt_area_3)
        txtResults3 = findViewById(R.id.txt_results_3)
    }

    private fun setTheErrorInputLayout(
        inputLayout : TextInputLayout,
        typeValidate : TypeValidate,
        typeInput : String) {
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
                inputLayout.error = if (typeInput == "volume") {
                    "É preciso digitar uma medida (kg/L/mL)!"
                } else if (typeInput == "area") {
                    "É preciso digitar uma medida (m²/cm²/m³/cm³)!"
                } else {
                    buildString {
                        append("É preciso digitar uma medida")
                        append("(L/m² ou L/cm² ou L/m³ ou L/cm³")
                        append("ou mL/m² ou mL/cm² ou mL/m³ ou mL/cm³")
                        append("ou kg/m² ou kg/cm² ou kg/m³ ou kg/cm³)")
                    }
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

        setTheErrorInputLayout(txtInputVolumeCalculate1, volumeValidationModel.typeValidate, "volume")
        setTheErrorInputLayout(txtInputAreaCalculate1, areaValidationModel.typeValidate, "area")

        val formIsValid = volumeValidationModel.isValid && areaValidationModel.isValid

        return formIsValid
    }

    private fun calculateVolumePerArea() {
        val isValid = validateToCalculateVolumePerArea()

        if (!isValid)
            return

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

    private fun validateToCalculateVolumePerArea2() : Boolean {
        txtInputCapacityContainer.isErrorEnabled = false
        txtInputVolumePerVolume.isErrorEnabled = false
        txtInputAreaCalculate2.isErrorEnabled = false

        val capacityContainer = txtEditCapacityContainer.text.toString()
        val volumePerVolume = txtEditVolumePerVolume.text.toString()
        val totalArea = txtEditAreaCalculate2.text.toString()

        val validationFormsVolumeArea = ValidationFormsVolumeArea()

        val cpCtrValidationModel = validationFormsVolumeArea.validateFormVolume(capacityContainer)
        val volPerVolValidationModel = validationFormsVolumeArea.validateFormVolume(volumePerVolume)
        val areaValidationModel = validationFormsVolumeArea.validateFormArea(totalArea)

        setTheErrorInputLayout(txtInputCapacityContainer, cpCtrValidationModel.typeValidate, "volume")
        setTheErrorInputLayout(txtInputVolumePerVolume, volPerVolValidationModel.typeValidate, "volume")
        setTheErrorInputLayout(txtInputAreaCalculate2, areaValidationModel.typeValidate, "area")

        val formIsValid = cpCtrValidationModel.isValid
                && volPerVolValidationModel.isValid
                && areaValidationModel.isValid

        return formIsValid
    }

    private fun calculateVolumePerArea2() {
        val validate = validateToCalculateVolumePerArea2()

        if (!validate)
            return
    }

    private fun expandedCalculateQuantityContainers() {
        isExpandedCalculateQuantityContainers = !isExpandedCalculateQuantityContainers

        if (isExpandedCalculateQuantityContainers) {
            expandedCalculateQuantityContainers.visibility = View.VISIBLE
        } else {
            expandedCalculateQuantityContainers.visibility = View.GONE
        }
    }

    private fun validateToQuantityContainers() : Boolean {
        txtInputCapacityContainer3.isErrorEnabled = false
        txtInputQuantityVolumePerArea.isErrorEnabled = false
        txtInputTotalArea3.isErrorEnabled = false

        val capacityContainer = txtEditCapacityContainer3.text.toString()
        val volumePerVolume = txtEditQuantityVolumePerArea.text.toString()
        val totalArea = txtEditTotalArea3.text.toString()

        val validationFormsVolumeArea = ValidationFormsVolumeArea()

        val cpCtrValidationModel = validationFormsVolumeArea.validateFormVolume(capacityContainer)
        val aPVValidationModel = validationFormsVolumeArea.validateFormVolumePerArea(volumePerVolume)
        val areaValidationModel = validationFormsVolumeArea.validateFormArea(totalArea)

        setTheErrorInputLayout(txtInputCapacityContainer3, cpCtrValidationModel.typeValidate, "volume")
        setTheErrorInputLayout(txtInputQuantityVolumePerArea, aPVValidationModel.typeValidate, "s")
        setTheErrorInputLayout(txtInputTotalArea3, areaValidationModel.typeValidate, "area")

        val formIsValid = cpCtrValidationModel.isValid
                && aPVValidationModel.isValid
                && areaValidationModel.isValid

        return formIsValid
    }

    private fun calculateQuantityContainers() {
        val validate = validateToQuantityContainers()

        if (!validate)
            return

        val capacityContainer = txtEditCapacityContainer3.text.toString()
        val volumePerArea = txtEditQuantityVolumePerArea.text.toString()
        val totalArea = txtEditTotalArea3.text.toString()

        val calculateClass = Calculate()
        val quantityContainers = calculateClass.quantityContainers(
            capacityContainer, volumePerArea, totalArea
        )

        val capacityContainerHtml = buildString {
            append("<b>Volume do recipiente: </b>")
            append("$capacityContainer.")
        }
        txtCapacityContainer3.text = Html.fromHtml(capacityContainerHtml, Html.FROM_HTML_MODE_LEGACY)

        val volumePerAreaHtml = buildString {
            append("<b>Quanto que o produto faz por área: </b>")
            append("$volumePerArea.")
        }
        txtProductVolumePerArea.text = Html.fromHtml(volumePerAreaHtml, Html.FROM_HTML_MODE_LEGACY)

        val area2Html = buildString {
            append("<b>Área total: </b>")
            append("$totalArea.")
        }
        txtArea3.text = Html.fromHtml(area2Html, Html.FROM_HTML_MODE_LEGACY)

        val result2Html = buildString {
            append("<b>Resultado final: </b>")
            append("quantidade de latas necessárias são $quantityContainers.")
        }
        txtResults3.text = Html.fromHtml(result2Html, Html.FROM_HTML_MODE_LEGACY)
    }

    override fun onStart() {
        super.onStart()

        cardCalculateVolumePerArea.setOnClickListener { expandedCalculateVolumePerArea() }

        btnCalculate1.setOnClickListener { calculateVolumePerArea() }

        val volumeHtml = buildString {
            append("<b>Volume: </b> (não informado).")
        }
        txtVolume.text = Html.fromHtml(volumeHtml, Html.FROM_HTML_MODE_LEGACY)

        val areaHtml = buildString {
            append("<b>Área: </b> (não informado).")
        }
        txtArea.text = Html.fromHtml(areaHtml, Html.FROM_HTML_MODE_LEGACY)

        val resultHtml = buildString {
            append("<b>Resultado: </b> (não calculado).")
        }
        txtVolumePerArea.text = Html.fromHtml(resultHtml, Html.FROM_HTML_MODE_LEGACY)

        cardCalculateVolumePerArea2.setOnClickListener { expandedCalculateVolumePerArea2() }

        btnCalculate2.setOnClickListener { calculateVolumePerArea2() }

        val capacityContainer = buildString {
            append("<b>Volume do recipiente: </b> (não informado).")
        }
        txtCapacityContainer.text = Html.fromHtml(capacityContainer, Html.FROM_HTML_MODE_LEGACY)

        val volumePerVolumeHtml = buildString {
            append("<b>Quantos mL o produto faz por litro: </b> (não informado).")
        }
        txtVolumePerVolume.text = Html.fromHtml(volumePerVolumeHtml, Html.FROM_HTML_MODE_LEGACY)

        val area2Html = buildString {
            append("<b>Área total: </b> (não informado).")
        }
        txtArea2.text = Html.fromHtml(area2Html, Html.FROM_HTML_MODE_LEGACY)

        val result2Html = buildString {
            append("<b>Resultado final: </b> (não calculado).")
        }
        txtResults2.text = Html.fromHtml(result2Html, Html.FROM_HTML_MODE_LEGACY)

        cardCalculateQuantityContainers.setOnClickListener { expandedCalculateQuantityContainers() }

        btnCalculate3.setOnClickListener { calculateQuantityContainers() }

        val capacity3Html = buildString {
            append("<b>Capacidade total da lata: </b> (não informado).")
        }
        txtCapacityContainer3.text = Html.fromHtml(capacity3Html, Html.FROM_HTML_MODE_LEGACY)

        val productVolumePerAreaHtml = buildString {
            append("<b>Quanto que o produto faz por área: </b> (não informado).")
        }
        txtProductVolumePerArea.text =
            Html.fromHtml(productVolumePerAreaHtml, Html.FROM_HTML_MODE_LEGACY)

        val area3Html = buildString {
            append("<b>Área total: </b> (não informado).")
        }
        txtArea3.text = Html.fromHtml(area3Html, Html.FROM_HTML_MODE_LEGACY)

        val results3Html = buildString {
            append("<b>Quantidade de latas necessárias: </b> (não calculado).")
        }
        txtResults3.text = Html.fromHtml(results3Html, Html.FROM_HTML_MODE_LEGACY)

        expandedCalculateVolumePerArea.visibility = View.GONE
        expandedCalculateVolumePerArea2.visibility = View.GONE
        expandedCalculateQuantityContainers.visibility = View.GONE
    }
}