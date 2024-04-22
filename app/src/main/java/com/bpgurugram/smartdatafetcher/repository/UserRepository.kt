package com.bpgurugram.smartdatafetcher.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bpgurugram.smartdatafetcher.api.UserApi
import com.bpgurugram.smartdatafetcher.models.UserList

class UserRepository(private var api : UserApi) {

    var mutableUserList = MutableLiveData<UserList>()
    val userLiveData : LiveData<UserList>
        get() = mutableUserList

    suspend fun getUsers()
    {
        val result = api.getUsers()
        if(result?.body() != null )
        {
            mutableUserList.postValue(result.body())
        }
    }
}