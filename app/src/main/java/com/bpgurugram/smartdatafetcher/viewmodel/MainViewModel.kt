package com.bpgurugram.smartdatafetcher.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bpgurugram.smartdatafetcher.models.UserList
import com.bpgurugram.smartdatafetcher.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(var userRepository: UserRepository) : ViewModel() {

    val userLiveData : LiveData<UserList>
        get() = userRepository.userLiveData

    init {
       viewModelScope.launch(Dispatchers.IO) {
           userRepository.getUsers()
       }
    }

    fun loadMoreItems()
    {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.getUsers()
        }
    }

    fun getTotalUsers() : Int?
    {
        return userLiveData.value?.size
    }

}