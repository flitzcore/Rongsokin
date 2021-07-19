package com.example.b1.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.example.b1.R
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_home, container, false)
        val rongsokin_barang= view.findViewById<CardView>(R.id.rongsokin_barang)
        val lokasi= view.findViewById<CardView>(R.id.cari_lokasi)
        val komunitas= view.findViewById<CardView>(R.id.komunitas)
        val dompet= view.findViewById<CardView>(R.id.dompet)

        rongsokin_barang.setOnClickListener {
            Toast.makeText(
                context,
                "rongsokin barang",
                Toast.LENGTH_SHORT)
                .show()
        }
        lokasi.setOnClickListener {
            Toast.makeText(
                context,
                "rongsokin barang",
                Toast.LENGTH_SHORT)
                .show()
        }
        komunitas.setOnClickListener {
            Toast.makeText(
                context,
                "rongsokin barang",
                Toast.LENGTH_SHORT)
                .show()
        }
        dompet.setOnClickListener {
            Toast.makeText(
                context,
                "rongsokin barang",
                Toast.LENGTH_SHORT)
                .show()
        }


        return view

    }

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}