package com.samoana.minimalistyoutube.di

import com.samoana.minimalistyoutube.ui.main.SearchResultsFragment
import com.samoana.minimalistyoutube.ui.main.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsBindingModule {
    @ContributesAndroidInjector
    abstract fun addEventFragment():HomeFragment

    @ContributesAndroidInjector
    abstract fun searchResultsFragment(): SearchResultsFragment
}