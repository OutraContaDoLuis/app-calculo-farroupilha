package com.dema.appcalculofarroupilha

import android.content.Context
import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.GridView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class CementitiousWaterproofingFragment : Fragment(), IFragmentOptions {

    private lateinit var gridSizeContainer : GridView
    private lateinit var txtInputLayer : TextInputLayout
    private lateinit var txtInputArea : TextInputLayout
    private lateinit var txtEditArea : TextInputEditText
    private lateinit var gridFCK : GridView
    private lateinit var btnCalculate : Button
    private lateinit var txtSize : TextView
    private lateinit var txtQuantity : TextView
    private var currentContext : Context? = null

    private var optionsSizeContainer = arrayListOf<OptionModel>(
        OptionModel("3Kg", true), OptionModel("15Kg", false)
    )
    private var currentOptionSizeContainerChecked = optionsSizeContainer[0]
    private var lastPositionSelectedOptionsSizeContainer = 0

    private var optionsDropdown = arrayListOf<String>(
        "Fundações e Lajes\n3 kg por 100m²",
        "Reboco de áreas molhadas\n3 kg por 100m²",
        "Reboco simples\n1 kg por 100m²",
        "Argamassas\n0.3 kg por 100m²",
        "Rejuntes cimentícios\n0.05 kg por 100m²",
    )
    private var currentOptionDropdown = -1

    private var optionsFCK = arrayListOf<OptionModel>(
        OptionModel("FCK 15", true), OptionModel("FCK 20", false),
        OptionModel("FCK 25", false), OptionModel("FCK 30", false),
        OptionModel("FCK 35", false), OptionModel("FCK 40", false),
        OptionModel("FCK 45", false), OptionModel("FCK 50", false),
    )
    private var currentOptionFCK = optionsFCK[0]
    private var lastPositionSelectedOptionsFck = 0

    private val tag = javaClass.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cementitious_waterproofing, container, false)

        currentContext = requireContext()

        gridSizeContainer = view.findViewById(R.id.grid_size_container)
        val gridSizeContainerAdapter =
            GridAdapter(currentContext!!, optionsSizeContainer, this, "sizeContainer")
        gridSizeContainer.adapter = gridSizeContainerAdapter

        txtInputLayer = view.findViewById(R.id.txt_input_layer)

        var arrayAdapter = ArrayAdapter(currentContext!!, R.layout.dropdown_item, optionsDropdown)
        var autocomplete = view.findViewById<AutoCompleteTextView>(R.id.layer_application)
        autocomplete.setAdapter(arrayAdapter)
        autocomplete.onItemClickListener = AdapterView.OnItemClickListener {
                parent, view, position, id ->
            currentOptionDropdown = position

            Log.v(tag, currentOptionDropdown.toString())
        }

        txtInputArea = view.findViewById(R.id.txt_input_area)
        txtEditArea = view.findViewById(R.id.txt_edit_area)

        gridFCK = view.findViewById(R.id.grid_fck)
        val gridFCKAdapter = GridAdapter(currentContext!!, optionsFCK, this, "fck")
        gridFCK.adapter = gridFCKAdapter

        btnCalculate = view.findViewById(R.id.btn_calculate)

        btnCalculate.setOnClickListener {
            calculate()
        }

        txtSize = view.findViewById(R.id.txt_size)
        val txtSizeBuildString = buildString {
            append("<b>Tamanho: </b>")
            append("(Não calculado)")
        }
        val txtSizeHtml = Html.fromHtml(txtSizeBuildString, Html.FROM_HTML_MODE_COMPACT)
        txtSize.text = txtSizeHtml

        txtQuantity = view.findViewById(R.id.txt_quantity)
        val txtQuantityBuildString = buildString {
            append("<b>Quantidade: </b>")
            append("(Não calculado)")
        }
        val txtQuantityHtml = Html.fromHtml(txtQuantityBuildString, Html.FROM_HTML_MODE_COMPACT)
        txtQuantity.text = txtQuantityHtml

        return view
    }

    private fun validation() : Boolean {
        var isValid = true

        if (currentOptionDropdown == -1) {
            isValid = false
            txtInputLayer.error = "Selecione o tipo de superfície"
        }

        if (txtEditArea.text.toString().isEmpty()) {
            isValid = false
            txtInputArea.error = "Digite uma área"
        }

        val txtSizeBuildString = buildString {
            append("<b>Tamanho: </b>")
            append(currentOptionFCK.text)
        }
        val txtSizeHtml = Html.fromHtml(txtSizeBuildString, Html.FROM_HTML_MODE_COMPACT)
        txtSize.text = txtSizeHtml

        return isValid
    }

    private fun calculate() {
        val validate = validation()

        if (!validate)
            return
    }

    override fun getPosition(position: Int, whichGrid : String) {
        if (whichGrid == "sizeContainer") {
            optionsSizeContainer[position].checked = true
            currentOptionSizeContainerChecked = optionsSizeContainer[position]
            optionsSizeContainer[lastPositionSelectedOptionsSizeContainer].checked = false
            lastPositionSelectedOptionsSizeContainer = position

            val gridSizeContainerAdapter =
                GridAdapter(currentContext!!, optionsSizeContainer, this, "sizeContainer")
            gridSizeContainer.adapter = gridSizeContainerAdapter
        } else {
            optionsFCK[position].checked = true
            currentOptionFCK = optionsFCK[position]
            optionsFCK[lastPositionSelectedOptionsFck].checked = false
            lastPositionSelectedOptionsFck = position
            val gridFCKAdapter = GridAdapter(currentContext!!, optionsFCK, this, "fck")
            gridFCK.adapter = gridFCKAdapter
        }
    }
}