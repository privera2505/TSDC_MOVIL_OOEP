package com.vinilo.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion

class RecyclerViewItemCountAssertion(private val expectedMinCount: Int) : ViewAssertion {
    override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
        if (noViewFoundException != null) throw noViewFoundException
        val recyclerView = view as RecyclerView
        val itemCount = recyclerView.adapter?.itemCount ?: 0
        assert(itemCount >= expectedMinCount) {
            "Expected at least $expectedMinCount items but found $itemCount"
        }
    }
}