package com.camehereforstickers.luckypoint.model

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

class LottoPointClusterItem(var latLng: LatLng): ClusterItem {

    override fun getSnippet(): String {
        return ""
    }

    override fun getTitle(): String {
        return ""
    }

    override fun getPosition(): LatLng {
        return latLng
    }

}