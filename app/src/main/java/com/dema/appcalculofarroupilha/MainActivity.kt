package com.dema.appcalculofarroupilha

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var txtEditVolumePerArea : EditText
    private lateinit var spinnerVolumePerAreaMeasure : Spinner
    private lateinit var txtEditContainerVolume : EditText
    private lateinit var spinnerContainerVolumeMeasure : Spinner
    private lateinit var txtEditTotalArea : EditText
    private lateinit var spinnerAreaTotalMeasure : Spinner
    private lateinit var btnCalculate : Button

    private var listOfOptionsVolumePerAreaMeasure : ArrayList<String> = arrayListOf(
        "L/m", "L/cm", "mL/m", "mL/cm"
    )
    private var currentVolumePerAreaMeasureSelected = ""

    private var listOfOptionsContainerVolumeMeasure : ArrayList<String> = arrayListOf(
        "L", "mL"
    )
    private var currentContainerVolumeMeasureSelected = ""

    private var listOfOptionsTotalArea : ArrayList<String> = arrayListOf(
        "m", "cm"
    )
    private var currentTotalAreaMeasureSelected = ""

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

        txtEditVolumePerArea = findViewById(R.id.txt_edit_volume_per_area)

        spinnerVolumePerAreaMeasure = findViewById(R.id.spinner_volume_per_area_measure)

        val adapterDropDownMenuVolumePerAreaMeasure = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            listOfOptionsVolumePerAreaMeasure)

        spinnerVolumePerAreaMeasure.adapter = adapterDropDownMenuVolumePerAreaMeasure
        spinnerVolumePerAreaMeasure.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                currentVolumePerAreaMeasureSelected = listOfOptionsVolumePerAreaMeasure[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                currentVolumePerAreaMeasureSelected = listOfOptionsVolumePerAreaMeasure[0]
            }

        }

        txtEditContainerVolume = findViewById(R.id.txt_edit_container_volume)

        spinnerContainerVolumeMeasure = findViewById(R.id.spinner_container_volume_measure)

        val adapterDropDownMenuContainerVolumeMeasure = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            listOfOptionsContainerVolumeMeasure)

        spinnerContainerVolumeMeasure.adapter = adapterDropDownMenuContainerVolumeMeasure
        spinnerContainerVolumeMeasure.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    currentContainerVolumeMeasureSelected = listOfOptionsContainerVolumeMeasure[p2]
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    currentContainerVolumeMeasureSelected = listOfOptionsContainerVolumeMeasure[0]
                }

            }

        txtEditTotalArea = findViewById(R.id.txt_edit_total_area)

        spinnerAreaTotalMeasure = findViewById(R.id.spinner_area_total_measure)

        val adapterDropDownMenuAreaTotalMeasure = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            listOfOptionsTotalArea)

        spinnerAreaTotalMeasure.adapter = adapterDropDownMenuAreaTotalMeasure
        spinnerAreaTotalMeasure.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    currentTotalAreaMeasureSelected = listOfOptionsTotalArea[p2]
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    currentTotalAreaMeasureSelected = listOfOptionsTotalArea[0]
                }

            }

        btnCalculate = findViewById(R.id.btn_calculate)
    }

    private fun validation() : Boolean {
        var isValid = txtEditVolumePerArea.text.toString().isNotEmpty()
                && txtEditContainerVolume.text.toString().isNotEmpty()
                && txtEditTotalArea.text.toString().isNotEmpty()

//        if (!isValid) {
//            Toast.makeText(this@MainActivity, "Digite todos os valores!", Toast.LENGTH_LONG).show()
//        }

        return isValid
    }

    private fun calculate() {
        val validate = validation()

//        if (validate)
//            return

        val volumePerArea = txtEditVolumePerArea.text.toString()
        val containerVolume = txtEditContainerVolume.text.toString()
        val totalArea = txtEditTotalArea.text.toString()

        if (volumePerArea.isNotEmpty() && containerVolume.isNotEmpty() && totalArea.isNotEmpty()) {
            var containerPerArea = containerVolume.toDouble() / volumePerArea.toDouble()
            var totalContainers = totalArea.toDouble() / containerPerArea
        }
    }

    override fun onStart() {
        super.onStart()

        btnCalculate.setOnClickListener { calculate() }
    }
}