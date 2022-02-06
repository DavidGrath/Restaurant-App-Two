package com.example.restaurantapptwo.main

import android.content.Context
import android.content.SharedPreferences
import com.example.restaurantapptwo.Constants

class BookmarkHelperImpl(val context: Context) : BookmarkHelper {
    private val preferences : SharedPreferences
    init {
        preferences = context.getSharedPreferences(Constants.APPLICATION_NAME, Context.MODE_PRIVATE)
    }
    override fun storeBookmarks(key: String, commaSepValues: String) {
        preferences.edit()
            .putString(key, commaSepValues)
            .apply()
    }

    override fun getBookmarks(key: String): HashSet<Int> {
        val bookmarks = preferences.getString(key, null)?:""
        val set = if(bookmarks.isEmpty()) {
            HashSet<Int>()
        } else {
            bookmarks.split(",")
                .map { it.toInt() }
                .toHashSet()
        }
        return set
    }
}