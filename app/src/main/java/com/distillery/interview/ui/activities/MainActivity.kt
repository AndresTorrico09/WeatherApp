package com.distillery.interview.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.distillery.interview.R
import com.distillery.interview.ui.fragments.CollectionWeatherFragment

class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.root, CollectionWeatherFragment())
                .commitAllowingStateLoss()
        }
    }
}
