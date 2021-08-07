package com.example.b1.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.example.b1.R
import com.example.b1.firestore.FirestoreClass
import com.example.b1.utils.Constants
import com.google.firebase.auth.FirebaseAuth


class SettingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_setting, container, false)
        val ganti_sandi= view.findViewById<CardView>(R.id.ganti_pass)
        val faq= view.findViewById<CardView>(R.id.faq)
        val kontak= view.findViewById<CardView>(R.id.kontak)
        val log_out= view.findViewById<CardView>(R.id.log_out)

        val sharedPreferences= activity?.getSharedPreferences(Constants.RONGSOKIN_PREFERENCES, Context.MODE_PRIVATE)
        val username= sharedPreferences?.getString(Constants.LOGGED_IN_USERNAME,"")!!
        val gmail= sharedPreferences?.getString(Constants.LOGGED_IN_GMAIL,"")!!
        val mobile= sharedPreferences?.getString(Constants.LOGGED_IN_MOBILE,"")!!
        val profil=view.findViewById<TextView>(R.id.nama_profil)
        val email=view.findViewById<TextView>(R.id.tv_mail)
        val telp=view.findViewById<TextView>(R.id.tv_telp)
        profil.text="Hi $username"
        email.text="$gmail"
        telp.text="$mobile"


        ganti_sandi.setOnClickListener {
            Toast.makeText(
                context,
                "ganti_sandi",
                Toast.LENGTH_SHORT)
                .show()
        }
        faq.setOnClickListener {
            Toast.makeText(
                context,
                "faq",
                Toast.LENGTH_SHORT)
                .show()
        }
        kontak.setOnClickListener {
            Toast.makeText(
                context,
                "kontak",
                Toast.LENGTH_SHORT)
                .show()
        }
        log_out.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(context, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)

        }
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            SettingFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}