package com.bpgurugram.smartdatafetcher.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.bpgurugram.smartdatafetcher.api.UserApi
import com.bpgurugram.smartdatafetcher.models.UserList
import com.bpgurugram.smartdatafetcher.models.UserListItem
import retrofit2.Response

class UserPagingSource(var api: UserApi) : PagingSource<Int, UserListItem>() {
    override fun getRefreshKey(state: PagingState<Int, UserListItem>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserListItem> {
         var ret : LoadResult<Int, UserListItem>
         return try {
            val pos = params.key ?: 1

            val response : Response<UserList> = api.getUsers(pos)
             LoadResult.Page(response.body()!!,if(pos  == 1) null else pos-1, if(pos  == 1) null else pos+1 )
        } catch (e: Exception) {
             LoadResult.Error(e)
        }

    }

}