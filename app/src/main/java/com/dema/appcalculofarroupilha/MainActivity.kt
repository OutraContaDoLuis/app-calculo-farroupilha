package com.dema.appcalculofarroupilha

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var cardCalculateVolumePerArea : CardView
    private lateinit var expandedCalculateVolumePerArea : LinearLayout
    private lateinit var cardCalculateVolumePerArea2 : CardView

    private var isExpandedCardCalculateVolumePerArea = false
    private var isExpandedCardCalculateVolumePerArea2 = false

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
        cardCalculateVolumePerArea2 = findViewById(R.id.card_calculate_volume_per_area_2)
    }

    private fun expandedCalculateVolumePerArea() {
        isExpandedCardCalculateVolumePerArea = !isExpandedCardCalculateVolumePerArea
        
        if (isExpandedCardCalculateVolumePerArea) {
            expandedCalculateVolumePerArea.visibility = View.VISIBLE
        } else {
            expandedCalculateVolumePerArea.visibility = View.GONE
        }
    }

    private fun expandedCalculateVolumePerArea2() {
        isExpandedCardCalculateVolumePerArea2 = !isExpandedCardCalculateVolumePerArea2
    }

    override fun onStart() {
        super.onStart()

        cardCalculateVolumePerArea.setOnClickListener { expandedCalculateVolumePerArea() }

        cardCalculateVolumePerArea2.setOnClickListener { expandedCalculateVolumePerArea2() }
    }
}