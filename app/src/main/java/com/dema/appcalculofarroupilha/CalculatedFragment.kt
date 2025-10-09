package com.dema.appcalculofarroupilha

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class CalculatedFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_calculated, container, false)

        val txtVolumePerArea = view.findViewById<TextView>(R.id.txt_volume_per_area)
        val txtContainerVolume = view.findViewById<TextView>(R.id.txt_container_volume)
        val txtTotalArea = view.findViewById<TextView>(R.id.txt_total_area)
        val txtResults = view.findViewById<TextView>(R.id.txt_results)

        return view
    }
}