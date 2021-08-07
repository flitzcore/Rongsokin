package com.example.b1.firestore

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import com.example.b1.activity.AddProductActivity
import com.example.b1.activity.LoginActivity
import com.example.b1.activity.RegisterActivity
import com.example.b1.model.Item
import com.example.b1.model.User
import com.example.b1.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class FirestoreClass {

    private  val mFireStore=FirebaseFirestore.getInstance()

    fun registerUser(activity: RegisterActivity, userInfo: User){
        mFireStore.collection(Constants.USERS)
            .document(userInfo.id)
            .set(userInfo, SetOptions.merge())
    }
    fun addItem(activity: AddProductActivity, userId: String, itemInfo: Item){
        mFireStore.collection(Constants.USERS).document(userId)
            .collection(Constants.PRODUCT).document(itemInfo.productId)
            .set(itemInfo, SetOptions.merge())
    }


    fun getCurrentUserID(): String{
        val currentUser= FirebaseAuth.getInstance().currentUser
        var currentUserID=""
        if(currentUser!=null)
        {
            currentUserID=currentUser.uid
        }
        return currentUserID
    }
    fun getUserDetails(activity: Activity){
        mFireStore.collection(Constants.USERS)
            .document(getCurrentUserID())
            .get()
            .addOnSuccessListener { document->
                val user =document.toObject(User::class.java)!!

                val sharedPreferences =
                    activity.getSharedPreferences(
                        Constants.RONGSOKIN_PREFERENCES,
                        Context.MODE_PRIVATE
                    )
                val editor: SharedPreferences.Editor=sharedPreferences.edit()
                editor.putString(
                    Constants.LOGGED_IN_USERNAME,
                    "${user.nama}"
                )
                editor.putString(
                    Constants.LOGGED_IN_GMAIL,
                    "${user.email}"
                )
                editor.putString(
                        Constants.LOGGED_IN_MOBILE,
                "${user.mobile}"
                )
                editor.apply()
                when(activity){
                    is LoginActivity->{
                        activity.userLoggedInSuccess(user)
                    }
                }

            }
    }


}