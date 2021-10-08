package com.example.popularlibraries.data

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserRepo(
    @Expose val id: Long? = null,
    @Expose val name: String? = null,
    @Expose val language: String? = null,
    @Expose val forks: Int? = null,
    @Expose val createdAt: String? = null
) : Parcelable
