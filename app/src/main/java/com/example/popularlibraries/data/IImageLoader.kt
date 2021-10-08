package com.example.popularlibraries.data

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}