package com.distillery.interview.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.distillery.interview.R

class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter()
        presenter.onStart(this)
    }
}
