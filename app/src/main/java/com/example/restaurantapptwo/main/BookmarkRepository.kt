package com.example.restaurantapptwo.main

import com.example.restaurantapptwo.Constants

class BookmarkRepository(private val bookmarkHelper: BookmarkHelper) {
    fun getRestaurantBookmarks() : HashSet<Int> {
        return bookmarkHelper.getBookmarks(Constants.Bookmarks.RESTAURANTS)
    }

    fun setRestaurantBookmarks(bookmarks : HashSet<Int>) {
        val string = bookmarks.joinToString(",")
        bookmarkHelper.storeBookmarks(Constants.Bookmarks.RESTAURANTS, string)
    }
}