package com.vinilo.utils

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.IdlingResource

class RecyclerViewItemCountIdlingResource(
    private val recyclerView: RecyclerView,
    private val minItemCount: Int = 1
) : IdlingResource {

    @Volatile
    private var callback: IdlingResource.ResourceCallback? = null

    override fun getName(): String = RecyclerViewItemCountIdlingResource::class.java.name

    override fun isIdleNow(): Boolean {
        val isIdle = recyclerView.adapter?.itemCount ?: 0 >= minItemCount
        if (isIdle) {
            callback?.onTransitionToIdle()
        }
        return isIdle
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        this.callback = callback
    }
}