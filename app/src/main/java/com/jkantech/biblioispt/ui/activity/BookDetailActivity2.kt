package com.jkantech.biblioispt.ui.activity

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import android.widget.Toolbar
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.jkantech.biblioispt.R
import com.jkantech.biblioispt.ui.Model.Book
import com.jkantech.biblioispt.ui.utils.*
import kotlinx.android.synthetic.main.activity_book_detail.*
import kotlinx.android.synthetic.main.nav_header_main.*
import org.json.JSONException
import org.json.JSONObject

class BookDetailActivity2 : AppCompatActivity() {
    var book_id:Int=0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val intent: Intent = getIntent()
        book_id = intent.getIntExtra("book_id", 0)


        put()
        toasMessage(this, book_id.toString())
        initView()
    }
    private fun initView(){
        val queue = Volley.newRequestQueue(this)
        val jsonParams = JSONObject()
       // jsonParams.put("id", book_id)




        val req = JsonArrayRequest(Request.Method.POST, getbook,null, Response.Listener{ response ->


            try {


                for(i in 0 until response.length()){
                    val row = response.getJSONObject(i)
                    val book = Book()

                    //book.id = row.getInt("id")
                    txttitle.text= row.getString("title")
                    txtCategory.text = row.getString("category")
                    txtshort_description.text =row.getString("short_description")
                    txtDescription.text = row.getString("description")
                    image.loadImage(row.getString("image"))
                    //book.image = row.getString("image")
                    book.url = row.getString("url")



                    Log.d("item","" + row.getString("title"))

                }
                //no_connexion.visibility=View.GONE


            }catch (e:Exception){
                e.message?.let { Log.d("Exception", it) }
                e.printStackTrace()
            }

        }, Response.ErrorListener { error ->

            //error.message?.let { Log.d("Exception in json reading operation", it) }
            // no_connexion.visibility=View.VISIBLE
            error.message?.let { Log.d("Erreur", it) }
            toasMessage(this,"Serveur non trouver")



        })








        queue.add(req)
      //  Api.instance?.addToRequestQueue(req)







    }
    private fun put(){
        //creating volley string request
        val queue = Volley.newRequestQueue(this)

        val stringRequest = object : StringRequest(Method.POST, getbook,
                Response.Listener<String> { response ->
                    try {
                        val obj = JSONObject(response)
                        Toast.makeText(applicationContext, obj.getString("message"), Toast.LENGTH_LONG).show()
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                },
                Response.ErrorListener { volleyError -> Toast.makeText(applicationContext, volleyError.message, Toast.LENGTH_LONG).show() }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params.put("id", book_id.toString())
                return params
            }
        }

        //adding request to queue
        queue.add(stringRequest)

    }






    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}