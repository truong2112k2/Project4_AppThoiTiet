package com.example.da4_appthoitiet.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.da4_appthoitiet.databinding.SpinnerItemBinding

class adpaterSpinner(val activity: Activity, val list: Array<String>) :
    ArrayAdapter<String>(activity, 0, list) {
    override fun getCount(): Int {
        return list.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createItemView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createItemView(position, convertView, parent)
    }


    private fun createItemView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = if (convertView == null) {
            SpinnerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        } else {
            SpinnerItemBinding.bind(convertView)
        } // khởi tạo biến binding
        binding.txtCountryName.text = list[position].toString()

        return binding.root
    }

}
