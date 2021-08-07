package com.example.b1.activity

import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.b1.R
import com.example.b1.firestore.FirestoreClass
import com.example.b1.model.Item
import com.example.b1.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.action_bar.*
import kotlinx.android.synthetic.main.activity_add_product.*
import java.lang.Exception
import java.util.*
import java.util.jar.Manifest
import kotlin.collections.HashMap

class AddProductActivity : AppCompatActivity() {
    private val CAMERA_REQUEST_CODE:Int =200
    private val STORAGE_REQUEST_CODE:Int =300
    private val IMAGE_PICK_GALLERY_CODE:Int =400
    private val IMAGE_PICK_CAMERA_CODE:Int =500

    lateinit var cameraPermission: Array<String>
    lateinit var storagePermission: Array<String>
    private var image_Uri: Uri? =null
    lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        setSupportActionBar(action_tool_bar)
        supportActionBar?.setTitle("")

        back_button_actionbar.setOnClickListener {
            val intent = Intent(this@AddProductActivity, RongsokActivity::class.java)
            startActivity(intent)
        }

        cameraPermission= arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        storagePermission= arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

        firebaseAuth= FirebaseAuth.getInstance()


        item_image.setOnClickListener{
            showImagePickDialog()
        }
        tv_kategori.setOnClickListener{
            categoryDialog()
        }
        tambahkan_barang.setOnClickListener{
            inputData()
        }
    }
    private lateinit var kategori:  String
    private lateinit var berat_sampah:  String
    private lateinit var deskripsi:  String


    private fun inputData(){
        kategori=tv_kategori.text.toString().trim({it<=' '})
        berat_sampah=et_berat_sampah.text.toString().trim({it<=' '})
        deskripsi=et_deskripsi.toString().trim({it<=' '})

        if(TextUtils.isEmpty(kategori)){
            Toast.makeText(
                this@AddProductActivity,
                "Harap memilih kategori",
                Toast.LENGTH_SHORT)
                .show()
            return
        }
        if(TextUtils.isEmpty(berat_sampah)){
            Toast.makeText(
                this@AddProductActivity,
                "Harap mengisi berat sampah",
                Toast.LENGTH_SHORT)
                .show()
            return
        }
        if(TextUtils.isEmpty(deskripsi)){
            Toast.makeText(
                this@AddProductActivity,
                "Harap mengisi deskripsi singkat",
                Toast.LENGTH_SHORT)
                .show()
            return
        }
        addProduct()
    }

    private  fun addProduct(){

        val timeStamp:String=""+System.currentTimeMillis()

        if(image_Uri==null){

            val item = Item(
                ""+firebaseAuth.uid,
                timeStamp,
                kategori,
                berat_sampah.toDouble(),
                deskripsi,
                "",
            )
            FirestoreClass().addItem(this@AddProductActivity, FirestoreClass().getCurrentUserID(),item)
        }
        else{
            val filePathAndName="product_images/"+""+timeStamp
            val storageReference=FirebaseStorage.getInstance().getReference(filePathAndName)

            storageReference.putFile(image_Uri!!)
                .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val downloadUri = task.result?.uploadSessionUri
                    val item = Item(
                        ""+firebaseAuth.uid,
                        timeStamp,
                        kategori,
                        berat_sampah.toDouble(),
                        deskripsi,
                        ""+downloadUri,
                    )

                    Log.d("TAG",downloadUri.toString()+"gggg")
                    ////\D/TAG: gs://rongsokin-97e5c.appspot.com/product_images/1628237604736

                    FirestoreClass().addItem(this@AddProductActivity, FirestoreClass().getCurrentUserID(),item)
                    ClearData()
                    val intent = Intent(this@AddProductActivity, RongsokActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                } else {
                    // Handle failures
                    // ...
                    Toast.makeText(
                        this@AddProductActivity,
                        task.exception.toString(),
                        Toast.LENGTH_SHORT)
                        .show()
                }
            }


        }

    }
    private fun ClearData(){
        tv_kategori.setText("")
        et_berat_sampah.setText("")
        et_deskripsi.setText("")
        item_image.setImageResource(R.drawable.ic_addphoto)
        image_Uri=null
    }
    private fun categoryDialog(){
        val builder= AlertDialog.Builder(this)
        builder.setTitle("Kategori Sampah").setItems(Constants.KATEGORI_BARANG){dialog,which ->
            when(which){
                0->categoryElektronik()
                1->categoryPlastik()
                2->categoryKertas()
                3->categoryKaca()
                4->categoryLogam()
                5->categoryKain()

            }
        }.show()
    }
    private fun categoryElektronik(){
        val builder= AlertDialog.Builder(this)
        builder.setTitle("Kategori Elektronik").setItems(Constants.BARANG_ELEKTRONIK){dialog,which ->
            val category= Constants.BARANG_ELEKTRONIK[which]
            tv_kategori.setText(category)
        }.show()
    }
    private fun categoryPlastik(){
        val builder= AlertDialog.Builder(this)
        builder.setTitle("Kategori Plastik").setItems(Constants.BARANG_PLASTIK){dialog,which ->
            val category= Constants.BARANG_PLASTIK[which]
            tv_kategori.setText(category)
        }.show()
    }
    private fun categoryKertas(){
        val builder= AlertDialog.Builder(this)
        builder.setTitle("Kategori Kertas").setItems(Constants.BARANG_KERTAS){dialog,which ->
            val category= Constants.BARANG_KERTAS[which]
            tv_kategori.setText(category)
        }.show()
    }
    private fun categoryKaca(){
        val builder= AlertDialog.Builder(this)
        builder.setTitle("Kategori Kaca").setItems(Constants.BARANG_KACA){dialog,which ->
            val category= Constants.BARANG_KACA[which]
            tv_kategori.setText(category)
        }.show()
    }
    private fun categoryLogam(){
        val builder= AlertDialog.Builder(this)
        builder.setTitle("Kategori Logam").setItems(Constants.BARANG_LOGAM){dialog,which ->
            val category= Constants.BARANG_LOGAM[which]
            tv_kategori.setText(category)
        }.show()
    }
    private fun categoryKain(){
        val builder= AlertDialog.Builder(this)
        builder.setTitle("Kategori Kain").setItems(Constants.BARANG_KAIN){dialog,which ->
            val category= Constants.BARANG_KAIN[which]
            tv_kategori.setText(category)
        }.show()
    }
    private fun showImagePickDialog(){
        val options: Array<String> = arrayOf("Kamera","Gallery")
        val builder= AlertDialog.Builder(this)
        builder.setTitle("Pilih Gambar").setItems(options) { dialog, which ->

            if(which==0){
                if(checkCameraPermission()){
                    pickFromCamera()
                }
                else{
                    requestCameraPermission()
                }
            }
            else{
                if(checkStoragePermission()){
                    pickFromGallery()
                }
                else{
                    requestStoragePermission()
                }
            }
        }.show()
    }
    private fun pickFromGallery(){
        val intent= Intent(Intent.ACTION_PICK)
        intent.setType("image/*")
        startActivityForResult(intent,IMAGE_PICK_GALLERY_CODE)
    }
    private fun pickFromCamera(){
        val contentValues= ContentValues()
        contentValues.put(MediaStore.Images.Media.TITLE,"Temp_Image_Title")
        contentValues.put(MediaStore.Images.Media.DESCRIPTION,"Temp_Image_Description")

        image_Uri= contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)!!
        intent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT,image_Uri)
        startActivityForResult(intent, IMAGE_PICK_CAMERA_CODE)

    }
    private fun checkStoragePermission(): Boolean{
        val result :Boolean=ContextCompat.checkSelfPermission(this@AddProductActivity, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)==(PackageManager.PERMISSION_GRANTED)
        return result
    }
    private fun requestStoragePermission(){
        ActivityCompat.requestPermissions(this,storagePermission,STORAGE_REQUEST_CODE)
    }
    private fun checkCameraPermission(): Boolean{
        val result :Boolean=ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)==(PackageManager.PERMISSION_GRANTED)
        val result1 :Boolean=ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)==(PackageManager.PERMISSION_GRANTED)
        return  result && result1
    }
    private fun requestCameraPermission(){
        ActivityCompat.requestPermissions(this,cameraPermission,STORAGE_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode){
            CAMERA_REQUEST_CODE->{
                if(grantResults.size>0){
                    val cameraAccepted:Boolean=grantResults[0]==PackageManager.PERMISSION_GRANTED
                    val storageAccepted:Boolean=grantResults[1]==PackageManager.PERMISSION_GRANTED
                    if(cameraAccepted && storageAccepted){
                        pickFromCamera()
                    }
                    else{
                        Toast.makeText(
                            this@AddProductActivity,
                            "Akses Kamera dan Penyimpanan dibutuhkan",
                            Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
            STORAGE_REQUEST_CODE->{
                if(grantResults.size>0){
                    val storageAccepted:Boolean=grantResults[0]==PackageManager.PERMISSION_GRANTED
                    if(storageAccepted){
                        pickFromGallery()
                    }
                    else{
                        Toast.makeText(
                            this@AddProductActivity,
                            "Akses Penyimpanan dibutuhkan",
                            Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode== RESULT_OK){
            if(requestCode == IMAGE_PICK_GALLERY_CODE){
                //img pick from gallery

                //save picked img
                image_Uri= data!!.getData()!!
                //set IMG
                item_image.setImageURI(image_Uri)
            }
            else if(requestCode==IMAGE_PICK_CAMERA_CODE){
                item_image.setImageURI(image_Uri)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}