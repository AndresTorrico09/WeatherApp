package com.distillery.interview.ui.search

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.distillery.interview.R
import com.distillery.interview.data.models.City
import com.distillery.interview.data.models.Result
import com.distillery.interview.data.models.SearchResponse
import com.distillery.interview.data.source.SearchRepository
import com.distillery.interview.data.source.remote.SearchLocalDataSource
import com.distillery.interview.databinding.FragmentSearchBinding
import com.distillery.interview.ui.MainActivity

class SearchFragment : Fragment(R.layout.fragment_search) {
    private val viewModel: SearchViewModel by viewModels {
        SearchViewModel.Factory(
            SearchRepository(SearchLocalDataSource())
        )
    }
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var binding: FragmentSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity() as MainActivity).supportActionBar?.hide()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentSearchBinding.bind(view)

        viewModel.getCities().observe(viewLifecycleOwner, { cityResponse ->
            when (cityResponse) {
                is Result.Loading -> {
                    showLoading()
                }
                is Result.Success -> {
                    hideLoading()
                    setValues(cityResponse.data)
                }
                is Result.Error -> {
                    hideLoading()
                    showError(cityResponse.exception?.message.toString())
                }
            }
        })

        setupRecyclerView()
    }

    private fun setValues(searchResponse: SearchResponse) {
        searchAdapter.setItems(searchResponse.cities as ArrayList<City>)
    }

    private fun setupRecyclerView() {
        searchAdapter = SearchAdapter()
        binding.rvSearchResults.adapter = searchAdapter
    }

    private fun showError(err: String) =
        Toast.makeText(requireContext(), err, Toast.LENGTH_LONG).show()

    private fun showLoading() =
        Toast.makeText(requireContext(), "startLoading", Toast.LENGTH_SHORT).show()

    private fun hideLoading() =
        Toast.makeText(requireContext(), "endLoading", Toast.LENGTH_SHORT).show()
}