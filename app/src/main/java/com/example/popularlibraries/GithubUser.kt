package com.example.popularlibraries

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubUser(
    val id: Long,
    val login: String
) : Parcelable
