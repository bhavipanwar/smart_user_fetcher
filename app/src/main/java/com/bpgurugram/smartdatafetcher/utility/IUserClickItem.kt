package com.bpgurugram.smartdatafetcher.utility

import com.bpgurugram.smartdatafetcher.models.UserListItem

 interface IUserClickItem {
    fun itemSelected(item : UserListItem)
    fun onScrollComplete(moreItem : Boolean)
}