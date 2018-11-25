package com.camehereforstickers.luckypoint


import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.location.Criteria
import android.location.LocationManager
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.camehereforstickers.luckypoint.api.APIBuilder
import com.camehereforstickers.luckypoint.model.LottoPlace
import com.camehereforstickers.luckypoint.model.LottoPointClusterItem
import com.camehereforstickers.luckypoint.model.Place
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.maps.android.clustering.ClusterManager
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.fragment_map.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.BufferedReader
import java.io.FileReader
import java.io.InputStreamReader
import java.io.Reader
import java.lang.reflect.Type


class MapFragment : Fragment(), OnMapReadyCallback{

    private lateinit var mMap : GoogleMap
    private var lottoPlacesList: List<Place>? = null
    private lateinit var lottoClusterManager: ClusterManager<LottoPointClusterItem>

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(context!!, R.raw.map_style))
        lottoClusterManager = ClusterManager(context, mMap)
        mMap.setOnCameraIdleListener(lottoClusterManager)
        mMap.setOnMarkerClickListener(lottoClusterManager)
        checkLocationPermisions()
        getLottoPlaces()
        //addCouponMarker(getUserLocation(), "3bhjb3iu")
        // Add a marker in Sydney, Australia, and move the camera.
        //val sydney = LatLng(-34.0, 151.0)
        //mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_map, container, false)

        view.map_view.onCreate(savedInstanceState)
        //view.map_view.onResume()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.map_view.getMapAsync(this)
    }

    override fun onResume() {
        super.onResume()
        view!!.map_view.onResume()
    }

    fun checkLocationPermisions(){
        Dexter.withActivity(this.activity)
            .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
            .withListener(object : PermissionListener {
                @SuppressLint("MissingPermission")
                override fun onPermissionGranted(response: PermissionGrantedResponse?) {
                    mMap.isMyLocationEnabled = true
                    if(getUserLocation() != LatLng(0.0,0.0))
                        focusCamera(getUserLocation(), 1.0)
                }

                override fun onPermissionRationaleShouldBeShown(
                    permission: PermissionRequest?,
                    token: PermissionToken?
                ) {
                    token!!.continuePermissionRequest()
                }

                override fun onPermissionDenied(response: PermissionDeniedResponse?) {
                    Snackbar.make(view!!, "Halo uzytkownik, Ty kurwo jebana", Snackbar.LENGTH_LONG).show()
                }

            }).check()
    }

    fun focusCamera(latLng: LatLng, boundsSize: Double){
        val latLngBounds = LatLngBounds(LatLng(latLng.latitude-boundsSize/2, latLng.longitude-boundsSize/2),
            LatLng(latLng.latitude+boundsSize/2, latLng.longitude+boundsSize/2))
        val cameraUpdate = CameraUpdateFactory.zoomTo(10f)
        val cameraPosition = CameraPosition.Builder().target(latLng).zoom(12f).build()
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
        //mMap.animateCamera()
    }

    @SuppressLint("MissingPermission")
    fun getUserLocation(): LatLng{
        return if(activity != null){
            val locationManager: LocationManager = activity!!.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            val userLocation = locationManager.getLastKnownLocation(locationManager.getBestProvider(Criteria(), false))
            LatLng(userLocation.latitude, userLocation.longitude)
        }else{
            LatLng(0.0,0.0)
        }
    }

    fun addCouponMarker(latLng: LatLng, id: String){
        mMap.addMarker(MarkerOptions()
            .title(id)
            .position(latLng)
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)))
    }

    fun getLottoPlaces(){
        var lottoList: List<LottoPlace>? = null
        if(activity != null){
            val raw = context!!.assets.open("kolektury.json")
            val reader = BufferedReader(InputStreamReader(raw, "UTF8"))
            val lottoListType = object : TypeToken<List<LottoPlace>>(){}.type
            //val founderListType = TypeToken<List<LottoPlace>>(){}.getType()
            lottoList = Gson().fromJson<List<LottoPlace>>(reader, lottoListType)
        }
        if(lottoList != null){
            var i = 0
            lottoList.forEach {
                i++
                if(i%7 == 0)
                    lottoClusterManager.addItem(LottoPointClusterItem(LatLng(it.lat, it.lng)))
            }
        }else{
            Log.e("Lotto List", "null")
        }
    }
}
