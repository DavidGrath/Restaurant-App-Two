package com.davidgrath.restaurantapptwo.location

import android.content.Context
import com.davidgrath.restaurantapptwo.location.entities.data.SimpleLocation
import org.apache.commons.csv.CSVFormat
import java.io.InputStreamReader

class LocationHelperImpl(context: Context) : LocationHelper {
    val locationList = ArrayList<SimpleLocation>()
    init {
        try {
            val inputStream = context.assets.open("lagos_lga_location_data.csv")
            val csvFormat = CSVFormat.Builder
                .create(CSVFormat.DEFAULT)
                .setHeader("LGA", "Lat", "Lng")
                .setSkipHeaderRecord(true)
                .build()
            val csvRecords = csvFormat.parse(InputStreamReader(inputStream))
            for (record in csvRecords) {
                val name = record.get("LGA")
                val lat = record.get("Lat")
                val lng = record.get("Lng")
                val location = SimpleLocation(name, directionToDegreeDecimal(lat), directionToDegreeDecimal(lng))
                locationList.add(location)
            }
        } catch (e : Exception) {
        }
    }

    private fun directionToDegreeDecimal(coordinate : String) : Double {
        val DEGREE_SIGN ="\u00B0"
        val index = coordinate.indexOf(DEGREE_SIGN)
        var value = coordinate.substring(0, index).toDouble()
        val direction = coordinate.substring(index+1)
        if(direction.equals("W", true) or direction.equals("S", true)) {
            value *= -1;
        }
        return value
    }
    override fun getSupportedLocations(): List<SimpleLocation> {
        return locationList
    }
}