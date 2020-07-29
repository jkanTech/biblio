package com.jkantech.biblioispt.ui.activity

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.Toolbar
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.JsonObject
import com.jkantech.biblioispt.R
import com.jkantech.biblioispt.ui.Model.Book
import com.jkantech.biblioispt.ui.utils.getbook
import com.jkantech.biblioispt.ui.utils.loadImage
import com.jkantech.biblioispt.ui.utils.toasMessage
import kotlinx.android.synthetic.main.activity_book_detail.*
import kotlinx.android.synthetic.main.nav_header_main.*
import org.json.JSONObject
import java.util.*
import kotlin.collections.HashMap

class BookDetailActivity : AppCompatActivity() {
    var book_id:Int=0
    lateinit var progressLayout:RelativeLayout
    var book:Book?=null
    lateinit var btnRead:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        progressLayout=findViewById(R.id.progressLayout)
        //progressLayout.visibility=View.VISIBLE
        btnRead=findViewById(R.id.btnRead)


        initView()
    }
    private fun initView(){
        book= Book()
        val intent=intent
        book!!.id= intent.getIntExtra("book_id", 0)
        book!!.title= intent.getStringExtra("title")
        book!!.category= intent.getStringExtra("category")
        book!!.short_description= intent.getStringExtra("short_description")
        book!!.description= intent.getStringExtra("description")
        book!!.image= intent.getStringExtra("image")
        book!!.url= intent.getStringExtra("url")
        book!!.format= intent.getStringExtra("format")

        txttitle.text= book!!.title
        txtCategory.text =book!!.category
        txtshort_description.text =book!!.short_description
        txtDescription.text =book!!.description
        image.loadImage(book!!.image)
        toasMessage(this, book!!.format.toString())


        btnRead.setOnClickListener{
            val intent=Intent(this,ReadActivity::class.java)
            intent.putExtra("url",book!!.url)
            intent.putExtra("format",book!!.format)
            startActivity(intent)
        }

    }






    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}