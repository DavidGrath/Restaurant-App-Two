package com.davidgrath.restaurantapptwo

import com.davidgrath.restaurantapptwo.restaurants.entities.RestaurantDetailsData
import com.davidgrath.restaurantapptwo.restaurants.entities.RestaurantSummaryUI
import java.text.SimpleDateFormat
import java.util.*

class ConverterUtils {
    companion object {
        fun restaurantDetailsDataToRestaurantSummaryUI(restaurant : RestaurantDetailsData) : RestaurantSummaryUI {
            val dateFormat = SimpleDateFormat("hh:mm a")
            dateFormat.timeZone = TimeZone.getTimeZone("GMT")
            val date = Date()
            date.time = restaurant.openTime * 60 * 1_000L
            val formatted = dateFormat.format(date)
            val openTimeText = "Open $formatted"
            return RestaurantSummaryUI(
                restaurant.id,
                restaurant.name,
                restaurant.address,
                openTimeText,
                restaurant.averageRating,
                null,
                null,
                null
            )
        }
    }
}