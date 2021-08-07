package com.example.b1.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.BaseAdapter
import android.widget.Toast
import com.example.b1.R
import com.example.b1.firestore.FirestoreClass
import com.example.b1.model.User
import com.example.b1.utils.BaseActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.masuk.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.masuk)


        button_daftar_sekarang.setOnClickListener {
            startActivity(Intent( this@LoginActivity, RegisterActivity::class.java))
            finish()
        }
        button_lupa_sandi.setOnClickListener {
            Toast.makeText(
                this@LoginActivity,
                "Kasihan :(",
                Toast.LENGTH_SHORT)
                .show()
        }
        masukButton.setOnClickListener {
            when{
                TextUtils.isEmpty(editTextEmailNoTelp.text.toString().trim{it<=' '})->{
                    Toast.makeText(
                        this@LoginActivity,
                        "Harap isi email atau nomor telpon",
                        Toast.LENGTH_SHORT)
                        .show()
                }
                TextUtils.isEmpty(editTextPass.text.toString().trim{it<=' '})->{
                    Toast.makeText(
                        this@LoginActivity,
                        "Harap isi password",
                        Toast.LENGTH_SHORT)
                        .show()
                }
                else->{
                    BaseActivity().showProgressDialog(this)
                    val email: String = editTextEmailNoTelp.text.toString().trim{it<=' '}
                    val password: String = editTextPass.text.toString().trim{it<=' '}

                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener{task->
                            BaseActivity().hideProgressDialog(this)
                            if(task.isSuccessful){
                                FirestoreClass().getUserDetails(this@LoginActivity)

                                Toast.makeText(
                                    this@LoginActivity,
                                    "Selamat Datang",
                                    Toast.LENGTH_SHORT)
                                    .show()
                            }else{
                                Toast.makeText(
                                    this@LoginActivity,
                                    task.exception!!.message.toString(),
                                    Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                }
            }
        }
    }

    fun userLoggedInSuccess(user: User){

        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        finish()
    }
}