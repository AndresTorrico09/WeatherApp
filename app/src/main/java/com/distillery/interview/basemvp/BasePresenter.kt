package com.distillery.interview.basemvp

import androidx.annotation.CallSuper

/**
 * Base interface for all MVP presenters.
 */
interface BasePresenter<View> {
    var view: View

    /**
     * Invoke on presenter start when view is created.
     */
    @CallSuper
    fun onStart(view: View) {
        this.view = view
    }
}
