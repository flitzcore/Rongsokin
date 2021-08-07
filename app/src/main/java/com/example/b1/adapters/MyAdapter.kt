package com.example.b1.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.b1.R
import com.example.b1.model.Item
import com.example.b1.model.Item_list
import com.google.firebase.storage.FirebaseStorage
import de.hdodenhof.circleimageview.CircleImageView

/*
class MyAdapter(private val listOfItem: ArrayList<Item_list>):RecyclerView.Adapter<MyAdapter.MyViewHolder>(){
    public class MyViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {

        val kategori: TextView=itemView.findViewById(R.id.tvKategori)
        val berat: TextView=itemView.findViewById(R.id.tvBerat)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val itemView= LayoutInflater.from(parent.context).inflate(R.layout.list_produk,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {
        val produk: Item_list=listOfItem[position]
        holder.kategori.text=produk.productCategory
        holder.berat.text=produk.productWeight.toString()
    }

    override fun getItemCount(): Int {
        return listOfItem.size
    }
}*/
class MyAdapter(private val context: Context,private val listOfItem: List<Item_list> ) : RecyclerView.Adapter<itemViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemViewHolder {
        return  itemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_produk,parent,false)
        )
    }

    override fun onBindViewHolder(holder: itemViewHolder, position: Int) {
        val produk=listOfItem[position]
        holder.kategori.text=produk.productCategory
        holder.berat.text=produk.productWeight.toString()

        val storage = FirebaseStorage.getInstance()
// Create a reference to a file from a Google Cloud Storage URI
       // val gsReference = storage.getReferenceFromUrl(produk.productIcon.toString())

        //img

        Log.d("TAG",produk.productIcon.toString()+"aaaaa")

        Glide.with(context)
            .load(produk.productIcon)
            .into(holder.gambarBarang)

    }

    override fun getItemCount(): Int {
        return listOfItem.size
    }

}
class itemViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    val kategori: TextView=itemView.findViewById(R.id.tvKategori)
    val berat: TextView=itemView.findViewById(R.id.tvBerat)
    val gambarBarang:CircleImageView=itemView.findViewById(R.id.img_product)
}