package com.distillery.interview.ui.search

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.distillery.interview.R
import com.distillery.interview.data.models.GeoLocation
import com.distillery.interview.data.models.HitsItem
import com.distillery.interview.data.models.Result
import com.distillery.interview.data.models.SearchResponse
import com.distillery.interview.data.source.SearchRepository
import com.distillery.interview.data.source.remote.SearchRemoteDataSource
import com.distillery.interview.databinding.FragmentSearchBinding
import com.distillery.interview.ui.MainActivity
import com.distillery.interview.ui.MainSharedViewModel

class SearchFragment : Fragment(R.layout.fragment_search), SearchAdapter.LocationClickable {
    private val viewModel: SearchViewModel by viewModels {
        SearchViewModel.Factory(
            SearchRepository(SearchRemoteDataSource())
        )
    }
    private val mainSharedViewModel: MainSharedViewModel by activityViewModels()
    private lateinit var searchAdapter: SearchAdapter
    private lateinit var binding: FragmentSearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity() as MainActivity).supportActionBar?.hide()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentSearchBinding.bind(view)
        setupRecyclerView()
        setupSearchView()
    }

    private fun setupSearchView() {
        binding.searchView.setIconifiedByDefault(false)
        binding.searchView.isIconified = false
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                searchCity(newText)
                return true
            }

            override fun onQueryTextSubmit(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun searchCity(newText: String?) {
        viewModel.getCities(newText).observe(viewLifecycleOwner, resultObserver)
    }

    private val resultObserver: Observer<Result<SearchResponse>> =
        Observer { searchResponse ->
            when (searchResponse) {
                is Result.Loading -> {
                    showLoading()
                }
                is Result.Success -> {
                    hideLoading()
                    setValues(searchResponse.data)
                }
                is Result.Error -> {
                    hideLoading()
                    showError(searchResponse.exception?.message.toString())
                }
            }
        }

    private fun setValues(searchResponse: SearchResponse) {
        //TODO: remove duplicates values
        searchAdapter.setItems(searchResponse.hits as ArrayList<HitsItem>)
    }

    private fun setupRecyclerView() {
        searchAdapter = SearchAdapter(this)
        binding.rvSearchResults.adapter = searchAdapter
    }

    override fun onClickLocation(loc: GeoLocation) {
        mainSharedViewModel.setLocation(loc.lat, loc.lon)
        requireActivity().onBackPressed()
    }

    private fun showError(err: String) =
        Toast.makeText(requireContext(), err, Toast.LENGTH_LONG).show()

    private fun showLoading() =
        Toast.makeText(requireContext(), "startLoading", Toast.LENGTH_SHORT).show()

    private fun hideLoading() =
        Toast.makeText(requireContext(), "endLoading", Toast.LENGTH_SHORT).show()

}