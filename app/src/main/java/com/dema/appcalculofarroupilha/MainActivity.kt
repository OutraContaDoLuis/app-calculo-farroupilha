package com.dema.appcalculofarroupilha

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var txtInputVolumePerArea : TextInputLayout
    private lateinit var txtEditVolumePerArea : TextInputEditText
    private lateinit var spinnerVolumePerAreaMeasure : Spinner
    private lateinit var txtInputContainerVolume : TextInputLayout
    private lateinit var txtEditContainerVolume : TextInputEditText
    private lateinit var spinnerContainerVolumeMeasure : Spinner
    private lateinit var txtInputTotalArea : TextInputLayout
    private lateinit var txtEditTotalArea : TextInputEditText
    private lateinit var spinnerAreaTotalMeasure : Spinner
    private lateinit var btnCalculate : Button

    private var listOfOptionsVolumePerAreaMeasure : ArrayList<String> = arrayListOf(
        "L/m", "L/cm", "mL/m", "mL/cm"
    )

    private var listOfOptionsContainerVolumeMeasure : ArrayList<String> = arrayListOf(
        "L", "mL"
    )

    private var listOfOptionsTotalArea : ArrayList<String> = arrayListOf(
        "m", "cm"
    )

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

        txtInputVolumePerArea = findViewById(R.id.txt_input_volume_per_area)
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

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }

        txtInputContainerVolume = findViewById(R.id.txt_input_container_volume)
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

                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }

            }

        txtInputTotalArea = findViewById(R.id.txt_input_total_area)
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

                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }

            }

        btnCalculate = findViewById(R.id.btn_calculate)
    }
}