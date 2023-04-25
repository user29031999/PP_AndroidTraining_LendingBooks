package com.iapolinarortiz.lendingbooks

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.widget.addTextChangedListener
import com.google.android.material.radiobutton.MaterialRadioButton
import com.google.android.material.textfield.TextInputEditText


class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val etName: TextInputEditText = findViewById(R.id.et_name)
        etName.addTextChangedListener { text -> name = text.toString() }
        val tvQuantity: TextView = findViewById(R.id.tv_quantity)
        val tvBorrowPrice: TextView = findViewById(R.id.tv_borrow_price)
        val tvTicket: TextView = findViewById(R.id.tv_ticket)
        val rdGroupCategory: RadioGroup = findViewById(R.id.rdgrp_categories)
        val rdBrandNew: MaterialRadioButton = findViewById(R.id.rd_brandnew)
        val rdUsed: MaterialRadioButton = findViewById(R.id.rd_used)
        rdGroupCategory.setOnCheckedChangeListener { _, checkedId ->
            isBrandNew = checkedId == R.id.rd_brandnew
            isUsed = checkedId == R.id.rd_used
            getTotalPrice(tvBorrowPrice)
        }
        val cvTicket: CardView = findViewById(R.id.cv_ticket)
        val btnOrder: Button = findViewById(R.id.btn_order)
        btnOrder.setOnClickListener {
            getTotalPrice(tvBorrowPrice)
            tvTicket.text = "Name: $name\n" +
                    "isSciFi: $isBrandNew\n" +
                    "isNovel: $isUsed\n" +
                    "Quantity: $quantity books\n" +
                    "Total price: $totalPrice"
            cvTicket.visibility = View.VISIBLE
        }
        val btnCancel: Button = findViewById(R.id.btn_cancel)
        btnCancel.setOnClickListener {
            name = "No name"
            quantity = 0
            isBrandNew = true
            isUsed = false
            tvQuantity.text = "Quantity: $quantity"
            enableOptions(btnOrder, rdBrandNew, rdUsed)
            getTotalPrice(tvBorrowPrice)
            cvTicket.visibility = View.GONE
            rdGroupCategory.check(rdBrandNew.id)
        }
        val btnDecreaseQuantity: Button = findViewById(R.id.btn_quantity_decrease)
        btnDecreaseQuantity.setOnClickListener {
            quantity = if (quantity <= 0) 0 else quantity - 1
            if (quantity == 0) {
                cvTicket.visibility = View.GONE
            }
            tvQuantity.text = "Quantity: $quantity"
            enableOptions(btnOrder, rdBrandNew, rdUsed)
            getTotalPrice(tvBorrowPrice)
        }
        val btnIncreaseQuantity: Button = findViewById(R.id.btn_quantity_increase)
        btnIncreaseQuantity.setOnClickListener {
            quantity += 1
            tvQuantity.text = "Quantity: $quantity"
            enableOptions(btnOrder, rdBrandNew, rdUsed)
            getTotalPrice(tvBorrowPrice)
        }

        enableOptions(btnOrder, rdBrandNew, rdUsed)
    }

    private fun enableOptions(
        btnOrder: Button,
        rdScienceFiction: MaterialRadioButton,
        rdNovel: MaterialRadioButton
    ) {
        btnOrder.isEnabled = quantity > 0
        rdScienceFiction.isEnabled = quantity > 0
        rdNovel.isEnabled = quantity > 0
    }

    private fun getTotalPrice(tvBorrowPrice: TextView) {
        totalPrice = BASE_PRICE * quantity
        if (quantity > 0) {
            if (isBrandNew) {
                totalPrice += SCIFI_PRICE
            }
            if (isUsed) {
                totalPrice += NOVEL_PRICE
            }
        }
        tvBorrowPrice.text = "Borrow price: $$totalPrice"
    }

    override fun onStart() {
        super.onStart()
        Log.d("onStart", "onStart getting called")
    }

    override fun onResume() {
        super.onResume()
        Log.d("onResume", "onResume getting called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("onPause", "onPause getting called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("onRestart", "onRestart getting called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("onDestroy", "onDestroy getting called")
    }

    companion object {
        const val BASE_PRICE = 10.00
        const val SCIFI_PRICE = 2.00
        const val NOVEL_PRICE = 1.00
        var name = "No name"
        var quantity = 0
        var isBrandNew = true
        var isUsed = false
        var totalPrice = 0.00
    }
}