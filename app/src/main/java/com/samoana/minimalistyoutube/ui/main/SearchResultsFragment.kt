package com.samoana.minimalistyoutube.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.samoana.minimalistyoutube.R
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_search_results.*
import javax.inject.Inject

class SearchResultsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MYTViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_results, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(arguments != null) {
            val searchQuery = arguments!!.getString("search_query")
            viewModel = ViewModelProviders.of(this, viewModelFactory).get(MYTViewModel::class.java)
            val resultsAdapter = ResultsAdapter(activity!!)
            search_results.layoutManager = LinearLayoutManager(activity)
            search_results.adapter = resultsAdapter
            viewModel.getSearchResults(searchQuery!!).observe(this, Observer {
                if(it !=null) {
                    progressBar.visibility = View.GONE
                    search_results.visibility = View.VISIBLE
                    resultsAdapter.updateEvents(it.items)
                }
            })
        }
    }


}
