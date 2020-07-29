package com.jkantech.biblioispt.ui.fragments.books

import android.annotation.SuppressLint
import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.jkantech.biblioispt.R
import com.jkantech.biblioispt.ui.Adapter.BookAdapter
import com.jkantech.biblioispt.ui.Model.Book
import com.jkantech.biblioispt.ui.activity.BookDetailActivity
import com.jkantech.biblioispt.ui.utils.getallbooks
import com.jkantech.biblioispt.ui.utils.getbook
import com.jkantech.biblioispt.ui.utils.toasMessage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_book_detail.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.include_no_network.*
import kotlinx.android.synthetic.main.item_book.*

class booksFragment : Fragment() {

    private lateinit var dashboardViewModel: booksViewModel
    lateinit var recyclerView: RecyclerView
    var bookList: ArrayList<Book> = ArrayList()
    lateinit var bookAdapter: BookAdapter
    lateinit var gridLayoutManager: GridLayoutManager
    lateinit var swipe:SwipeRefreshLayout
    lateinit var no_connexion: RelativeLayout

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        dashboardViewModel =
                ViewModelProvider(this).get(booksViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_book, container, false)
         swipe=root.findViewById<SwipeRefreshLayout>(R.id.refresh)
        no_connexion=root.findViewById(R.id.no_connexion)
        swipe.isRefreshing=true

        swipe.setOnRefreshListener {
            no_connexion.visibility=View.GONE
            callView()

        }


         initView(root)

        return root
    }

    @SuppressLint("LongLogTag")
    fun initView(view: View) {
        val queue = Volley.newRequestQueue(activity as Context)


        val req = JsonArrayRequest(Request.Method.POST, getallbooks,null, Response.Listener{ response ->

            try {


                for(i in 0 until response.length()){
                    val row = response.getJSONObject(i)
                    val book = Book()

                    book.id = row.getInt("id")
                    book.title = row.getString("title")
                    book.category = row.getString("category")
                    book.short_description =row.getString("short_description")
                    book.description = row.getString("description")
                    book.image = row.getString("image")
                    book.url = row.getString("url")
                    book.format = row.getString("format")


                    bookList.add(book)

                    Log.d("item","" + row.getString("title"))

                }
                swipe.isRefreshing=false
                no_connexion.visibility=View.GONE
                bookAdapter.notifyDataSetChanged()


            }catch (e:Exception){
                e.message?.let { Log.d("Exception", it) }
                e.printStackTrace()
            }

        }, Response.ErrorListener { error ->
            swipe.isRefreshing=false

            //error.message?.let { Log.d("Exception in json reading operation", it) }
            no_connexion.visibility=View.VISIBLE
            error.message?.let { Log.d("Erreur", it) }
            context?.let { toasMessage(it,"Serveur non trouver") }



        })





        recyclerView = view.book_recycleView

        recyclerView.setHasFixedSize(true)
        gridLayoutManager =
            GridLayoutManager(requireContext(), 2, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = gridLayoutManager
        bookAdapter = BookAdapter(requireActivity(), bookList)
        recyclerView.adapter = bookAdapter

        queue.add(req)


    }
    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.book_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_refresh->{
               callView()

                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun bookDatail(bookIndex: Int) {

        if (bookIndex < 0) {
            return
        }
        //val book = this.bookList.removeAt(bookIndex)
       // val book = this.bookList.
      //  val note = this.bookList.
      //  val test=this.bookList.

       //category.id?.let { dbManager.delete(it) }

        activity?.let {

            //val intent = Intent(it, MainQuiz::class.java),REQUEST_CODE)
            val intent = Intent(it, BookDetailActivity::class.java)
            //intent.putExtra("id", book.id)
            it.startActivity(intent)


        }
        context?.let {
          //  toasMessage(it,book.id.toString())
        }

    }

    private fun callView(){
        view?.let { initView(it) }
        bookList.clear()


    }


}