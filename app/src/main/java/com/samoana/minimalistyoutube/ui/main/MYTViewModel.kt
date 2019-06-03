package com.samoana.minimalistyoutube.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.samoana.minimalistyoutube.data.MYTResult
import com.samoana.minimalistyoutube.repository.MYTRepository
import javax.inject.Inject

class MYTViewModel @Inject constructor(private val mytRepository: MYTRepository) : ViewModel() {

   fun getSearchResults(searchQuery : String) : LiveData<MYTResult> = mytRepository.getSearchResults(searchQuery)

}
