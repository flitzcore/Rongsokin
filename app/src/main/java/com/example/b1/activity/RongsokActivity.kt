package com.example.b1.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.b1.R
import com.example.b1.adapters.MyAdapter
import com.example.b1.databinding.ActivityRongsokBinding
import com.example.b1.firestore.FirestoreClass
import com.example.b1.model.Item
import com.example.b1.model.Item_list
import com.example.b1.utils.Constants
import com.google.firebase.firestore.*
import kotlinx.android.synthetic.main.action_bar.*
import kotlinx.android.synthetic.main.activity_rongsok.*

class RongsokActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRongsokBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(action_tool_bar)
        supportActionBar?.setTitle("")

        binding= ActivityRongsokBinding.inflate(layoutInflater)


        setContentView(binding.root)

        binding.rvRongsok.apply{
            layoutManager=LinearLayoutManager(this@RongsokActivity)
        }

        fetchData()


        back_button_actionbar.setOnClickListener {
            val intent = Intent(this@RongsokActivity, MainActivity::class.java)
            startActivity(intent)
        }
        add_button.setOnClickListener{
            val intent = Intent(this@RongsokActivity, AddProductActivity::class.java)
            startActivity(intent)
        }
    }

    private fun fetchData() {
        FirebaseFirestore.getInstance()
            .collection(Constants.USERS).document(FirestoreClass().getCurrentUserID())
            .collection(Constants.PRODUCT)
            .get()
            .addOnSuccessListener { documents ->
                for(document in documents){
                    val item =documents.toObjects(Item_list::class.java)
                    binding.rvRongsok.adapter=MyAdapter(this,item)
                }
            }
            .addOnFailureListener{e->
                Toast.makeText(
                    this@RongsokActivity,
                    e.message.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
    }
}