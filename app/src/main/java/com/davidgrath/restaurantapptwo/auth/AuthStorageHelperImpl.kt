package com.davidgrath.restaurantapptwo.auth

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.davidgrath.restaurantapptwo.auth.entities.data.UserData
import com.davidgrath.restaurantapptwo.auth.entities.data.UserDataPrivate
import com.davidgrath.restaurantapptwo.auth.entities.data.UserDataPublic
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject

class AuthStorageHelperImpl(private val context: Context) : AuthStorageHelper {

    //TODO, so I felt the only other way to do this was to store the whole object and then
    // differentiate changed fields with equals() and deliver the individual updates, but I felt
    // that may be more complicated and that I was thinking of Redux too much
    private val userDataObservable : Observable<UserData>
    private val tokenBehaviorSubject = BehaviorSubject.create<String>()
    private val bookmarksBehaviorSubject = BehaviorSubject.create<List<String>>()
    private val creditCardsBehaviorSubject = BehaviorSubject.create<List<String>>()
    private val deliveryAddressesBehaviorSubject = BehaviorSubject.create<List<String>>()
    private val locationBehaviorSubject = BehaviorSubject.create<String?>()
    private val usernameBehaviorSubject = BehaviorSubject.create<String>()
    private val emailBehaviorSubject = BehaviorSubject.create<String>()
    private val verifiedBehaviorSubject = BehaviorSubject.create<Boolean>()
    private val reviewsBehaviorSubject = BehaviorSubject.create<List<String>>()

    private val listener = SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
        when(key) {
            "token" -> {
                val token = sharedPreferences.getString("token", null)
                tokenBehaviorSubject.onNext(token?:"")
            }
            "bookmarks" -> {
                val string = sharedPreferences.getString("bookmarks", "")?:""
                //Not just empty
                val bookmarks = if(string.isBlank()) {
                    emptyList<String>()
                } else {
                    string.split(",")
                }
                bookmarksBehaviorSubject.onNext(bookmarks)
            }
            "creditCards" -> {
                val string = sharedPreferences.getString("creditCards", "")?:""
                //Not just empty
                val bookmarks = if(string.isBlank()) {
                    emptyList<String>()
                } else {
                    string.split(",")
                }
                bookmarksBehaviorSubject.onNext(bookmarks)
            }
            "deliveryAddresses" -> {
                val string = sharedPreferences.getString("deliveryAddresses", "")?:""
                //Not just empty
                val bookmarks = if(string.isBlank()) {
                    emptyList<String>()
                } else {
                    string.split(",")
                }
                bookmarksBehaviorSubject.onNext(bookmarks)
            }
            "location" -> {
                val location = sharedPreferences.getString("location", null)
                locationBehaviorSubject.onNext(location?:"")
            }
            "username" -> {
                val username = sharedPreferences.getString("username", null)
                usernameBehaviorSubject.onNext(username?:"")
            }
            "email" -> {
                val email = sharedPreferences.getString("email", null)
                emailBehaviorSubject.onNext(email?:"")
            }
            "verified" -> {
                val verified = sharedPreferences.getBoolean("verified", false)
                verifiedBehaviorSubject.onNext(verified)
            }
            "reviews" -> {
                val string = sharedPreferences.getString("reviews", "")?:""
                //Not just empty
                val reviews = if(string.isBlank()) {
                    emptyList<String>()
                } else {
                    string.split(",")
                }
                reviewsBehaviorSubject.onNext(reviews)
            }
        }
    }
    private val preferences: SharedPreferences

    init {
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
        preferences = EncryptedSharedPreferences.create(
            context,
            "user_data",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
        preferences.registerOnSharedPreferenceChangeListener(listener)
        //TODO Remove this line
        preferences.edit().clear().commit()

        usernameBehaviorSubject.onNext(preferences.getString("username", ""))
        emailBehaviorSubject.onNext(preferences.getString("email", ""))
        verifiedBehaviorSubject.onNext(preferences.getBoolean("verified", false))
        reviewsBehaviorSubject.onNext(emptyList())

        tokenBehaviorSubject.onNext(preferences.getString("token", ""))
        bookmarksBehaviorSubject.onNext(emptyList())
        creditCardsBehaviorSubject.onNext(emptyList())
        deliveryAddressesBehaviorSubject.onNext(emptyList())
        locationBehaviorSubject.onNext(preferences.getString("location", ""))

        userDataObservable = Observable.combineLatest(tokenBehaviorSubject, bookmarksBehaviorSubject, creditCardsBehaviorSubject, deliveryAddressesBehaviorSubject,
            locationBehaviorSubject, usernameBehaviorSubject, emailBehaviorSubject, verifiedBehaviorSubject, reviewsBehaviorSubject, {token, bookmarks, cards, addresses, location, username, email, verified, reviews ->
            UserData(
                UserDataPublic(username, email, verified, null, reviews),
                UserDataPrivate(token, bookmarks, cards, addresses, location)
            )
        })
    }

    override fun storeToken(token: String) {
        preferences.edit()
            .putString("token", token)
            .apply()
    }

    override fun getToken(): String {
        return preferences.getString("token", null)?:""
    }

    override fun setLocation(location: String) {
        preferences.edit()
            .putString("location", location)
            .apply()
    }

    override fun getLocation(): String {
        return preferences.getString("location", null)?:""
    }

    override fun storeUserData(userData: UserData) {
        val public = userData.publicData
        val reviewsString = public.reviews.joinToString(",")
        val private = userData.privateData
        val bookmarksString = private.bookmarks.joinToString(",")
        val creditCardsString = private.creditCards.joinToString(",")
        val deliveryAddressesString = private.deliveryAddresses.joinToString(",")
        preferences.edit()
            .putString("username", public.username)
            .putString("email", public.email)
            .putBoolean("verified", public.verified)
            .putString("reviews", reviewsString)
            .putString("token", private.authToken)
            .putString("bookmarks", bookmarksString)
            .putString("creditCards", creditCardsString)
            .putString("deliveryAddresses", deliveryAddressesString)
            .apply()
    }

    override fun getStoredUserData(): Observable<UserData> {
        return userDataObservable
    }

    override fun getBookmarks(): Observable<List<String>> {
        return bookmarksBehaviorSubject
    }
}