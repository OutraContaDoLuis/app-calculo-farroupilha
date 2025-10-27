package com.dema.appcalculofarroupilha

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.card.MaterialCardView

class GridAdapter(context : Context, listOptions : ArrayList<OptionModel>, fragment : Fragment,
                  whichGrid : String) : BaseAdapter() {

    private var context : Context? = null
    private var listOptions : ArrayList<OptionModel> = arrayListOf()
    private var fragment : Fragment = Fragment()
    private var whichGrid : String = ""

    init {
        this.context = context
        this.listOptions = listOptions
        this.fragment = fragment
        this.whichGrid = whichGrid
    }

    private var inflater: LayoutInflater? = null

    override fun getCount(): Int {
        return listOptions.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    @SuppressLint("InflateParams")
    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?
    ): View? {
        if (inflater == null)
            inflater = context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?

        var newConvertView = convertView

        if (newConvertView == null)
            newConvertView = inflater?.inflate(R.layout.item_grid, null)

        val mCardView = newConvertView?.findViewById<MaterialCardView>(R.id.material_card_view)

        val txtText = newConvertView?.findViewById<TextView>(R.id.txt_text)
        txtText?.text = listOptions[position].text

        if (listOptions[position].checked) {
            mCardView?.setBackgroundColor(context?.resources!!.getColor(R.color.black))
            txtText?.setTextColor(context?.resources!!.getColor(R.color.white))
        }

        mCardView?.setOnClickListener {
            (fragment as? IFragmentOptions)?.getPosition(position, whichGrid)
        }

        return newConvertView
    }
}