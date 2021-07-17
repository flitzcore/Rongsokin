package com.example.b1.activity

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.example.b1.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.daftar.*
import kotlinx.android.synthetic.main.masuk.*


class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.daftar)
        button_login_sekarang.setOnClickListener {
            startActivity(Intent( this@RegisterActivity, LoginActivity::class.java))
            finish()
        }
        buatAkun.setOnClickListener {
            when
            {
                !syaratketentuan.isChecked->{
                    Toast.makeText(
                        this@RegisterActivity,
                        "Harap Menyetujui Syarat dan Ketentuan",
                        Toast.LENGTH_SHORT)
                        .show()
                }
                TextUtils.isEmpty(tvUsername.text.toString().trim{it<= ' ' })->{
                    Toast.makeText(
                        this@RegisterActivity,
                        "Isi Username",
                        Toast.LENGTH_SHORT)
                        .show()
                }
                TextUtils.isEmpty(tvAlamatemail.text.toString().trim{it<= ' ' })->{
                    Toast.makeText(
                        this@RegisterActivity,
                        "Isi Email",
                        Toast.LENGTH_SHORT)
                        .show()
                }
               !Patterns.EMAIL_ADDRESS.matcher(tvAlamatemail.getText().toString()).matches()->{
                   Toast.makeText(
                       this@RegisterActivity,
                       "Invalid Email",
                       Toast.LENGTH_SHORT)
                       .show()
               }
                TextUtils.isEmpty(tvNomorseluler.text.toString().trim{it<= ' ' })->{
                    Toast.makeText(
                        this@RegisterActivity,
                        "Isi Nomor Seluler",
                        Toast.LENGTH_SHORT)
                        .show()
                }
                TextUtils.isEmpty(Password.text.toString().trim{it<= ' ' })->{
                    Toast.makeText(
                        this@RegisterActivity,
                        "Isi Nomor Seluler",
                        Toast.LENGTH_SHORT)
                        .show()
                }
                else ->{
                    val username: String =tvUsername.text.toString().trim{it<= ' '}
                    val email: String =tvAlamatemail.text.toString().trim{it<= ' '}
                    val nomorSeluler: String =tvNomorseluler.text.toString().trim{it<= ' '}
                    val password: String =Password.text.toString().trim{it<= ' '}

                    if(syaratketentuan.isChecked){
                        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password).addOnCompleteListener(
                            {task ->
                                if(task.isSuccessful){
                                    val firebaseUser: FirebaseUser = task.result!!.user!!

                                    Toast.makeText(
                                        this@RegisterActivity,
                                        "Berhasil Mendaftar",
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                    intent.putExtra("user_id", firebaseUser.uid)
                                    intent.putExtra("email_id", email)
                                    startActivity(intent)
                                    finish()
                                }else{
                                    Toast.makeText(
                                        this@RegisterActivity,
                                        "Berhasil Mendaftar",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        )
                    }

                }
            }


        }

        val text ="Saya menyetujui syarat dan ketentuan aplikasi ini"
        val spanString= SpannableString(text)
        val clickableSpan=object : ClickableSpan()
        {
            override fun onClick(widget: View) {
                Toast.makeText(this@RegisterActivity, "Belum makai syarat",Toast.LENGTH_SHORT).show()

            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color=Color.RED
            }
        }
        spanString.setSpan(clickableSpan,16,36,Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        skText.text=spanString
        skText.movementMethod=LinkMovementMethod.getInstance()
    }
}





