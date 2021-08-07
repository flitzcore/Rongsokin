package com.example.b1.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.example.b1.R
import com.example.b1.utils.Constants
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

        val sharedPreferences= activity?.getSharedPreferences(Constants.RONGSOKIN_PREFERENCES, Context.MODE_PRIVATE)
        val username= sharedPreferences?.getString(Constants.LOGGED_IN_USERNAME,"")!!

        val profil=view.findViewById<TextView>(R.id.profil)
        profil.text="Hi $username"


        rongsokin_barang.setOnClickListener {
            val intent = Intent(context, RongsokActivity::class.java)
            //intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
        lokasi.setOnClickListener {
            val intent = Intent(context, MapsActivity::class.java)
            //intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
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