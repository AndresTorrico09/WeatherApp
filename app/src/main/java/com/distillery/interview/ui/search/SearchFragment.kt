package com.distillery.interview.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.distillery.interview.R
import com.distillery.interview.ui.MainActivity

class SearchFragment : Fragment(R.layout.fragment_search) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity() as MainActivity).supportActionBar?.hide()
    }
}