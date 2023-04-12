package com.davidgrath.restaurantapptwo.location.entities.data

data class SimpleLocation(
    val name : String,
    val lat : Double,
    val lng: Double
) {
    override fun toString(): String {
        return "$name,$lat,$lng"
    }

    companion object {
        fun fromString(locationString: String) : SimpleLocation {
            val split = locationString.split(",")
            return SimpleLocation(
                split[0],
                split[1].toDouble(),
                split[2].toDouble(),
            )
        }
    }
}