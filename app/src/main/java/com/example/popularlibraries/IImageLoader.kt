package com.example.popularlibraries

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}