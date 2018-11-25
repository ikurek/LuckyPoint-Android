package com.camehereforstickers.luckypoint.model

import com.google.android.gms.maps.model.LatLng

data class Place(
    var name: String,
    var geometry: Geometry,
    var id: String
)