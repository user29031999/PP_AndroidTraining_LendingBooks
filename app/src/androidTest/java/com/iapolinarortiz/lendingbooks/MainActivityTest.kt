package com.iapolinarortiz.lendingbooks

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun visibleComponents() {
        onView(withId(R.id.et_name)).check(matches(isDisplayed()))
        onView(withId(R.id.rdgrp_categories)).check(matches(isDisplayed()))
        onView(withId(R.id.rd_brandnew)).check(matches(isDisplayed()))
        onView(withId(R.id.rd_used)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_quantity)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_quantity_increase)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_quantity_decrease)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_borrow_price)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_order)).check(matches(isDisplayed()))
        onView(withId(R.id.btn_cancel)).check(matches(isDisplayed()))
    }

    @Test
    fun enterName() {
        onView(withId(R.id.et_name)).perform(typeText("Isidro"), ViewActions.closeSoftKeyboard())
            .check(
                matches(withText("Isidro"))
            )
    }

    @Test
    fun validateEnabledControls() {
        onView(withId(R.id.rd_brandnew)).check(matches(isNotEnabled()))
        onView(withId(R.id.rd_used)).check(matches(isNotEnabled()))
        onView(withId(R.id.btn_order)).check(matches(isNotEnabled()))
        onView(withId(R.id.btn_quantity_increase)).perform(click())
        onView(withId(R.id.rd_brandnew)).check(matches(isEnabled()))
        onView(withId(R.id.rd_used)).check(matches(isEnabled()))
        onView(withId(R.id.btn_order)).check(matches(isEnabled()))
    }

    @Test
    fun validateLending() {
        val name = "Isidro Apolinar"
        val totalPrice = "12.0"
        onView(withId(R.id.et_name)).perform(
            typeText(name),
            ViewActions.closeSoftKeyboard()
        ).check(
            matches(withText(name))
        )
        onView(withId(R.id.btn_quantity_increase)).perform(click())
        onView(withId(R.id.tv_quantity)).check(matches(withText("Quantity: 1")))
        onView(withId(R.id.tv_borrow_price)).check(matches(withText("Borrow price: $$totalPrice")))
        onView(withId(R.id.btn_order)).perform(click())

        onView(withId(R.id.tv_ticket)).check(
            matches(
                withText(
                    String.format(
                        TICKET_MESSAGE,
                        name,
                        "true",
                        "false",
                        "1",
                        totalPrice
                    )
                )
            )
        )
    }

    @Test
    fun cancelLendingOrder() {
        val name = "Isidro Apolinar"
        val totalPrice = "12.0"
        onView(withId(R.id.et_name)).perform(
            typeText(name),
            ViewActions.closeSoftKeyboard()
        ).check(
            matches(withText(name))
        )
        onView(withId(R.id.btn_quantity_increase)).perform(click())
        onView(withId(R.id.tv_quantity)).check(matches(withText("Quantity: 1")))
        onView(withId(R.id.tv_borrow_price)).check(matches(withText("Borrow price: $$totalPrice")))
        onView(withId(R.id.btn_order)).perform(click())

        onView(withId(R.id.tv_ticket)).check(
            matches(
                withText(
                    String.format(
                        TICKET_MESSAGE,
                        name,
                        "true",
                        "false",
                        "1",
                        totalPrice
                    )
                )
            )
        )

        onView(withId(R.id.btn_cancel)).perform(click())
        onView(withId(R.id.rd_brandnew)).check(matches(isNotEnabled()))
        onView(withId(R.id.rd_used)).check(matches(isNotEnabled()))
        onView(withId(R.id.tv_quantity)).check(matches(withText("Quantity: 0")))
        onView(withId(R.id.tv_borrow_price)).check(matches(withText("Borrow price: $0.0")))
        onView(withId(R.id.btn_order)).check(matches(isNotEnabled()))
    }

    companion object {
        const val TICKET_MESSAGE: String = "Name: %s\n" +
                "isSciFi: %s\n" +
                "isNovel: %s\n" +
                "Quantity: %s books\n" +
                "Total price: %s"
    }
}