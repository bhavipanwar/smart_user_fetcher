package com.bpgurugram.smartdatafetcher.models.parceable

import android.os.Parcel
import android.os.Parcelable
import com.bpgurugram.smartdatafetcher.models.UserListItem

import kotlinx.parcelize.Parcelize

@Parcelize
data class UserListItemParceable(var userId : String,var id : String,var userTitle : String, var userBody : String) : Parcelable {


}