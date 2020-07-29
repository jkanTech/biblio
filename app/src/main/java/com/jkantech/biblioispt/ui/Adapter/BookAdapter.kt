package com.jkantech.biblioispt.ui.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.jkantech.biblioispt.R
import com.jkantech.biblioispt.ui.Model.Book
import com.jkantech.biblioispt.ui.activity.BookDetailActivity
import com.jkantech.biblioispt.ui.activity.BookDetailActivity2
import com.jkantech.biblioispt.ui.utils.loadImage


class BookAdapter(val context : Context, val books : ArrayList<Book>) : RecyclerView.Adapter<BookAdapter.DashboardViewHolder>(){

    class DashboardViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val cardView = itemView.findViewById(R.id.card_book_item) as CardView
        val titleView = cardView.findViewById(R.id.title) as TextView
        val image=cardView.findViewById<ImageView>(R.id.image)
        val category = cardView.findViewById(R.id.category) as TextView
        val shortDescription = cardView.findViewById(R.id.short_description) as TextView


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book,parent,false)
        return DashboardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        val book = books[position]
        holder.cardView.tag = position
        holder.titleView.text = book.title
        holder.category.text = book.category
        holder.shortDescription.text = book.short_description
        holder.image.loadImage(book.image)

        holder.cardView.setOnClickListener {
           // Toast.makeText(context,"clicked on ${book.id}",Toast.LENGTH_SHORT).show()
            val intent = Intent(context, BookDetailActivity::class.java)
            intent.putExtra("book_id", book.id)
            intent.putExtra("title", book.title)
            intent.putExtra("category", book.category)
            intent.putExtra("short_description", book.short_description)
            intent.putExtra("description", book.description)
            intent.putExtra("image", book.image)
            intent.putExtra("url", book.url)
            intent.putExtra("format", book.format)

            context.startActivity(intent)
        }
    }
}