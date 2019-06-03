package com.samoana.minimalistyoutube.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.samoana.minimalistyoutube.R
import dagger.android.support.DaggerFragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : DaggerFragment(),View.OnClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MYTViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MYTViewModel::class.java)
        search.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when(view.id) {
            R.id.search -> {
                val bundle = Bundle()
                bundle.putString("search_query", search_query.text.toString())
                findNavController(this).navigate(R.id.action_mainFragment_to_searchResultsFragment, bundle)
            }
        }
    }

}
