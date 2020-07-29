package com.jkantech.biblioispt.ui.fragments.home

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jkantech.biblioispt.R
import com.jkantech.biblioispt.ui.Adapter.BookAdapter
import com.jkantech.biblioispt.ui.Model.Book
import com.jkantech.biblioispt.ui.utils.toasMessage

class HomeFragment : Fragment(),View.OnClickListener {

    //private lateinit var homeViewModel: HomeViewModel
    lateinit var recyclerView: RecyclerView
    var bookList: ArrayList<Book> = ArrayList()
    lateinit var bookAdapter:BookAdapter
    lateinit var gridLayoutManager:GridLayoutManager


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        return view
    }



    override fun onClick(p0: View?) {
        toast("Encours d'implementation")

    }
    private fun toast(message:String){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }


}