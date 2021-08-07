package com.example.b1.utils

import android.app.Dialog
import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.b1.R
import kotlin.coroutines.coroutineContext

class BaseActivity(): AppCompatActivity() {
    private lateinit var mProgressDialog: Dialog
    fun showProgressDialog(context:Context){
        mProgressDialog=Dialog(context)
        mProgressDialog.setContentView(R.layout.dialog_progress)
        mProgressDialog.setCancelable(false)
        mProgressDialog.setCanceledOnTouchOutside(false)
        mProgressDialog.show()
        Log.d("TAG","showIt")

    }
    fun hideProgressDialog(context:Context){
        Log.d("TAG","hideIt")
        mProgressDialog=Dialog(context)
        mProgressDialog.dismiss()
    }
}
