package com.bpgurugram.smartdatafetcher.api

import com.bpgurugram.smartdatafetcher.models.UserList
import com.bpgurugram.smartdatafetcher.models.UserListItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {
    @GET("posts")
    suspend fun getUsers() : Response<UserList>

    @GET("posts")
    suspend fun getUsers(@Query("page") page : Int) : Response<UserList>
}