package com.distillery.interview.main

class MainPresenter : MainContract.Presenter {

    override lateinit var view: MainContract.View

    override fun onStart(view: MainContract.View) {
        super.onStart(view)
        this.view = view
    }
}
