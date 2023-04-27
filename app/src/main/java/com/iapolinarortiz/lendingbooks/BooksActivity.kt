package com.iapolinarortiz.lendingbooks

import android.app.Activity
import android.app.Instrumentation.ActivityResult
import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.iapolinarortiz.lendingbooks.data.Books
import com.iapolinarortiz.lendingbooks.databinding.ActivityBooksBinding
import java.util.*

class BooksActivity : AppCompatActivity() {
    private lateinit var activityBooksBinding: ActivityBooksBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBooksBinding = ActivityBooksBinding.inflate(layoutInflater)
        setContentView(activityBooksBinding.root)

        val bookOne: Books = Books("The great gatsby", Books.NOVEL)
        val bookTwo: Books = Books("Anna Karenina", Books.NOVEL)
        val bookThree: Books = Books("Dune", Books.SCIFI)
        val bookNovel: Books = Books("Salem's Lot", Books.NOVEL)
        val bookBios: Books = Books("A Beautiful Mind", Books.BIOS)

        val booksAdapter: ArrayAdapter<String> = ArrayAdapter(
            this,
            R.layout.list_item,
            listOf(
                bookOne.toString(),
                bookTwo.toString(),
                bookThree.toString(),
                bookNovel.toString(),
                bookBios.toString()
            )
        )
        activityBooksBinding.lvBooks.adapter = booksAdapter
        activityBooksBinding.lvBooks.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val selectedBook: String? = booksAdapter.getItem(position)
            val intent = Intent()
            intent.putExtra("book", selectedBook)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

    }
}