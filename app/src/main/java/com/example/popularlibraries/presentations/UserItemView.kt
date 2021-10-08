package com.example.popularlibraries.presentations

interface UserItemView: IItemView {
    fun setLogin(text: String)
    fun loadAvatar(url: String)
}