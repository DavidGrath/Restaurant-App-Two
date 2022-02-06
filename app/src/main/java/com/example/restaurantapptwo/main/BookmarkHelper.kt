package com.example.restaurantapptwo.main

interface BookmarkHelper {
    fun storeBookmarks(key : String, commaSepValues : String)
    fun getBookmarks(key : String) : HashSet<Int>
}